package au.com.user.access.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class UserAccessService {

    /**
     * Executes a Powershell script.
     *
     * @param scriptFilename the filename of the script
     * @param args any arguments to pass to the script
     * @return the result as String.
     * @throws Exception if an error occurs
     */
    public UserBean executePSScript(String scriptFilename, String args) throws Exception {
        if (!new File(scriptFilename).exists())
            throw new Exception("Script file doesn't exist: " + scriptFilename);

        String cmd = "cmd /c powershell -ExecutionPolicy RemoteSigned -file \"" + scriptFilename + "\"";
        if (args != null && args.length() > 0)
            cmd += " " + args;
        return new UserBean(exec(cmd));
    }

    private static String exec(String command) throws Exception {
        StringBuffer sbInput = new StringBuffer();
        StringBuffer sbError = new StringBuffer();

        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command);
        proc.getOutputStream().close();
        InputStream inputstream = proc.getInputStream();
        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

        String line;
        while ((line = bufferedreader.readLine()) != null) {
            sbInput.append(line + "\n");
        }

        inputstream = proc.getErrorStream();
        inputstreamreader = new InputStreamReader(inputstream);
        bufferedreader = new BufferedReader(inputstreamreader);
        while ((line = bufferedreader.readLine()) != null) {
            sbError.append(line + "\n");
        }

        if (sbError.length() > 0)
            throw new Exception("The command [" + command + "] failed to execute!\n\nResult returned:\n" + sbError.toString());

        return "The command [" + command + "] executed successfully!\n\nResult returned:\n" + sbInput.toString();
    }
}


