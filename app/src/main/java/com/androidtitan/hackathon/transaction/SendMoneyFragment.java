package com.androidtitan.hackathon.transaction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtitan.hackathon.App;
import com.androidtitan.hackathon.R;
import com.androidtitan.hackathon.myApi.model.TransferToken;
import com.androidtitan.hackathon.server.InitiateTransferAsync;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendMoneyFragment extends Fragment {

    public static SendMoneyFragment newInstance() {
        return new SendMoneyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send_money, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @OnClick(R.id.create_code_button)
    public void submit(View view) {

        TransferToken token = new TransferToken();
        token.setPayer(App.payer);
        token.setAmount(100.00);
        new InitiateTransferAsync(getActivity()).execute(token);


    }
}
