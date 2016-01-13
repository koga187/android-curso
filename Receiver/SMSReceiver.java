package br.com.caelum.cadastro.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;

import br.com.caelum.cadastro.DAO.AlunoDAO;
import br.com.caelum.cadastro.R;

public class SMSReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent data) {
        Bundle bundle = data.getExtras();

        Object[] mensagens = (Object[]) bundle.get("pdus");
        byte[] pdu = (byte[]) mensagens[0];

        SmsMessage sms = SmsMessage.createFromPdu(pdu);

        String telefone = sms.getOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);

        if(dao.isAluno(telefone)) {
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
        }
    }
}
