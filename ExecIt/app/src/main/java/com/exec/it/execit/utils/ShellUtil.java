package com.exec.it.execit.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 1130665 on 7/28/2017.
 */

public class ShellUtil {

    public static String shellDo(String command) {
        try {
            Process su = Runtime.getRuntime().exec("su\n");

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(su.getInputStream()));
					
			//DataOutputStream outputStream = 

            int read;
            char[] buffer = new char[4096];
            StringBuffer log = new StringBuffer();
            while ((read = bufferedReader.read(buffer)) > 0) {
                log.append(buffer, 0, read);
            }
            bufferedReader.close();

            su.waitFor();

            return log.toString();
        } catch (Exception e) {
            Log.e("Exception: ", e.getMessage());
            return null;
        }
    }

/*    public static void killSU() {
        if (su != null) {
            su.destroy();
        }
    }
*/

    /*public static void execSU() {
        try {
            su = Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
}
