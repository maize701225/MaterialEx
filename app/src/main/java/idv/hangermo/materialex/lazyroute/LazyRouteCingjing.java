package idv.hangermo.materialex.lazyroute;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import idv.hangermo.materialex.R;

/**
 * Created by user on 2015/7/10.
 */
public class LazyRouteCingjing extends AppCompatActivity{
    private FrameLayout frameLayout;
    private Toolbar toolbar;
    private TextView textView6;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lazyout_cingjing);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView6=(TextView)findViewById(R.id.textView6);

        Bundle bundle = getIntent().getExtras();
        String value=bundle.getString("msg");
        textView6.setText(value);


    }
}
