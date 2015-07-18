package idv.hangermo.materialex.lazyroute;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import idv.hangermo.materialex.R;

/**
 * Created by user on 2015/7/10.
 */
public class LazyRouteTownship extends FragmentActivity {
    private FrameLayout frameLayout;
    private TextView tvTownship1, tvTownship2, tvTownship3 ,tvTownship4;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lazyroute_central_taiwan_township);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        tvTownship1 = (TextView) findViewById(R.id.tvTownship1);
        tvTownship2 = (TextView) findViewById(R.id.tvTownship2);
        tvTownship3 = (TextView) findViewById(R.id.tvTownship3);
        tvTownship4 = (TextView) findViewById(R.id.tvTownship4);

        Bundle bundle = getIntent().getExtras();
        String value=bundle.getString("msg");

    }

}
