package com.exec.it.execit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.exec.it.execit.R;
import com.exec.it.execit.utils.RootUtil;
import com.exec.it.execit.utils.ShellUtil;

public class HomeActivity extends Activity {

    TextView noRootText, resultText;
    RelativeLayout headerLayout;
    EditText commandText;
    Button execButton;
    ScrollView scrollResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        checkRoot();

        execButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execCommand(commandText.getText().toString());
            }
        });
    }

    private void init() {
        headerLayout = (RelativeLayout) findViewById(R.id.headerLayout);
        noRootText = (TextView) findViewById(R.id.noRootText);
        resultText = (TextView) findViewById(R.id.resultText);

        commandText = (EditText) findViewById(R.id.commandText);
        execButton = (Button) findViewById(R.id.execButton);

        scrollResult = (ScrollView) findViewById(R.id.scrollResult);
    }

    private void checkRoot() {
        if (!RootUtil.isDeviceRooted()) {
            headerLayout.setVisibility(View.GONE);
            noRootText.setVisibility(View.VISIBLE);
        } else {
            ShellUtil.execSU();
        }
    }

    @Override
    protected void onStop() {
        ShellUtil.killSU();
        super.onStop();
    }

    private void execCommand(String command) {
        if (command != null) {
            if (!("".equals(command.trim()))) {
                String result = ShellUtil.shellDo(command);
                if (result != null) {
                    if (!("".equals(result))) {
                        scrollResult.setVisibility(View.VISIBLE);
                        resultText.setText(result);
                        Toast.makeText(HomeActivity.this, "Command executed!", Toast.LENGTH_SHORT).show();
                    } else {
                        scrollResult.setVisibility(View.GONE);
                        Toast.makeText(HomeActivity.this, "Command executed with result 'Okay'", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, "Empty command!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
