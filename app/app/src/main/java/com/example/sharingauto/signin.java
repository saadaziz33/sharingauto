package com.example.sharingauto;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharingauto.Retro.myclient;
import com.example.sharingauto.Retro.myservice;
import com.rengwuxian.materialedittext.MaterialEditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class signin extends Fragment {

    EditText email, pass;
    Button signinbtn;


    CompositeDisposable cd = new CompositeDisposable();
    myservice myservice;

    public signin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.fragment_signin, container, false);
        return rootview;
    }

    @Override
    public void onStop() {
        cd.clear();
        super.onStop();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit myretroclient = myclient.getInstance();
        myservice = myretroclient.create(myservice.class);

        email = (EditText) view.findViewById(R.id.email);
        pass = (EditText) view.findViewById(R.id.pass);
        signinbtn = (Button) view.findViewById(R.id.singinbtn);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinUser(
                        email.getText().toString(),
                        pass.getText().toString()
                );
                //Navigation.findNavController(view).navigate(R.id.action_signin_to_mainmenu);
            }
        });
    }

    private void signinUser(String email, String pass) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getActivity(), "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(getActivity(), "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        cd.add(myservice.signinuser(email, pass)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String response) throws Exception {
                Toast.makeText(getActivity(), "" + response, Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
