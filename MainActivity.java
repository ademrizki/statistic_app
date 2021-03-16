package com.example.win7.statistic;

import android.view.View;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView dataview,datanomor,datamean,datamedian,datavarians,datadeviation,dataskewness,datakurtosis;
    EditText inputdata;
    ScrollView scroll;
//Membuat array list untuk menampung data
    ArrayList<Double> lst= new ArrayList<Double>();
    int no=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //menghubungkan view dengan variabel
        setContentView(R.layout.activity_main);
        dataview=(TextView) findViewById(R.id.textData);
        datanomor=(TextView) findViewById(R.id.textNomor);
        inputdata=(EditText) findViewById(R.id.editText1);
        datamean=(TextView) findViewById(R.id.textMean);
        datamedian=(TextView) findViewById(R.id.textMedian);
        datavarians=(TextView) findViewById(R.id.textVarians);
        datadeviation=(TextView) findViewById(R.id.textDeviation);
        dataskewness=(TextView) findViewById(R.id.textSkewness);
        datakurtosis=(TextView) findViewById(R.id.textKurtosis);
        datanomor.setText("Nomor\n");
        dataview.setText("Data\n");
    }
    public void add(View view)
    {
        scroll=(ScrollView) findViewById(R.id.scrolling);
        Double data = Double.parseDouble(inputdata.getText().toString());
        //setiap klik OK data masuk array
        lst.add(data);
        no++;
        //Menampilkan data yang dimasukkan
        datanomor.append(no+"\n");
        dataview.append(data+"\n");
        //supaya scroll view otomatis ke bawah
        scroll.fullScroll(View.FOCUS_DOWN);
        inputdata.setText("");
        inputdata.requestFocus();
    }
    public void descriptive(View view)
    {
        //set Text output
        datamean.setText("Mean : "+mean());
        datamedian.setText("Median : "+median());
        datavarians.setText("Variansi : "+varians());
        datadeviation.setText("Simpangan Baku: "+deviation());
    }
            //cari rata-rata
    private Double mean()
    {
        double tampung=0.0;
        double mean;
        for(int i=0;i<lst.size();i++)
        {
            tampung+=lst.get(i);
        }
        mean=tampung/lst.size();
        Log.e("mean",mean+"");
        return roundTwoDecimals(mean);
    }
            //cari median
    private Double median()
    {
        //sort arraylist
        Collections.sort(lst);
        int middle = lst.size()/2;
        double median;
        if (lst.size()%2 == 1) {
            median=lst.get(middle);
        } else {
            median=(lst.get(middle-1) + lst.get(middle)) / 2.0;
        }
        return roundTwoDecimals(median);
    }
    //cari varians
    private Double varians()
    {
        double mean = mean();
        double temp = 0;
        for(int a=0;a<lst.size();a++)
        {
            temp += (mean-lst.get(a))*(mean-lst.get(a));
        }
        return roundTwoDecimals(temp/lst.size());
    }
    //cari standar deviasi
    private Double deviation()
    {
        return roundTwoDecimals(Math.sqrt(varians()));
    }

    //Membulatkan jadi dua angka dibelakang koma
    double roundTwoDecimals(double d) {
        double twoDForm = Math.round(d*100)/100.00;
        return (twoDForm);
    }

    public void clear(View view)
    {
        datanomor.setText("Nomor\n");
        dataview.setText("Data\n");
        datamean.setText("");
        datamedian.setText("");
        datavarians.setText("");
        datadeviation.setText("");
        no=0;
        lst.clear();
    }
}

