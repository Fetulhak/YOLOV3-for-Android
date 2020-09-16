package it.unipr.scarpentim.pasmtftest1.img;

import android.os.Environment;

import org.opencv.core.Mat;
import android.util.Log;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.graphics.Bitmap;

public class ImageSaver {

    DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
    public static final String FOLDER_NAME = "pasmApp";
    private static final String TAG = "dddddddddddd";
    File dir = null;
    Date previousTime = null;
    int previousProg = 0;

    public void createFolderIfNotExist() {
        dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), FOLDER_NAME);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public boolean save(Mat mat) {
        Mat ultimate = new Mat();
        Imgproc.cvtColor(mat, ultimate, Imgproc.COLOR_RGB2BGR);

        Date currentTime = Calendar.getInstance().getTime();
        String strProg = "";
        if (currentTime.equals(previousTime)) {
            strProg = String.valueOf(++previousProg);
        }else{
            previousProg = 0;
        }
        String fullPath = String.format("%s/recognition_%s%s.jpg", dir.getAbsolutePath(), df.format(currentTime), strProg);
        previousTime = currentTime;
        return Imgcodecs.imwrite(fullPath, ultimate);
    }

    public void save2(Bitmap finalBitmap) {
        //Mat ultimate = new Mat();
        //Imgproc.cvtColor(mat, ultimate, Imgproc.COLOR_RGB2BGR);

        Date currentTime = Calendar.getInstance().getTime();
        String strProg = "";
        if (currentTime.equals(previousTime)) {
            strProg = String.valueOf(++previousProg);
        }else{
            previousProg = 0;
        }
        String fullPath = String.format("%s/recognitionfducker.jpg", dir.getAbsolutePath());
        Log.i(TAG, "I saved it to location ------"+ fullPath);
        String filename="fetulhak";
        try {

            File file = new File(fullPath, filename+".jpg");
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();

        String fname = "fetuuuuuuuuuu-"+ 0 +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
