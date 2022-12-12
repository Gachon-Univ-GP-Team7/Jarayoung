package com.example.ourapplication.Utils;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class LoadFiles extends Thread{
    private ArrayList<File> documentFiles;
    private File file;
    private final String ExternalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private int fileCnt;

    @Override
    public void run() {
        super.run();
        documentFiles = new ArrayList<>();
        DocumentPathRead(ExternalPath);
    }

    public int DocumentPathRead(String path)
    {
        int cnt = 0;
        file = new File(path);
        File[] files = file.listFiles();
        String innerPath;

        for(int i = 0; i < Objects.requireNonNull(files).length; i++){
            if(files[i].isDirectory()) {
                innerPath = files[i].getAbsolutePath();
                int tmp = fileCnt;
                fileCnt += DocumentPathRead(innerPath);
                if (fileCnt - tmp == 0) {
                    continue;
                }
            }
            else
            {
                if(files[i].isFile()){
                    if(files[i].getName().endsWith(".hwp") || files[i].getName().endsWith(".doc") || files[i].getName().endsWith(".xlsx") || files[i].getName().endsWith(".pptx") || files[i].getName().endsWith(".pdf"))
                    {
                        documentFiles.add(files[i]);
                        cnt++;
                    }
                }
            }

        }
        return cnt;
    }
}
