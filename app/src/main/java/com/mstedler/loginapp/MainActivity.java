package com.mstedler.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mstedler.loginapp.pojo.User;
import com.mstedler.loginapp.util.SessionManager;
import com.mstedler.loginapp.util.UIUtils;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;

    @BindView(R.id.tvNome)
    TextView tvNome;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        User user = Paper.book().read("user", null);
        sessionManager = new SessionManager(this);
        if(user != null) {
            tvNome.setText(user.getName());
            Glide.with(this).load(user.getPhoto_url()).into(ivPhoto);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_logout) {
            UIUtils.showAlertBuilder(this, null,  getString(R.string.logout), "Deseja sair?", "Sim", "Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_POSITIVE) {
                        logout();
                    }
                }
            }, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {

                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        sessionManager.setLogged(false);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
