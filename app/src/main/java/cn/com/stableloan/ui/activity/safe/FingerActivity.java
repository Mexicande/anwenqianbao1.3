package cn.com.stableloan.ui.activity.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.stableloan.R;
import cn.com.stableloan.ui.fragment.dialogfragment.FingerFragment;

public class FingerActivity extends AppCompatActivity {

    private FingerFragment fingerFragment;
    public static void launch(Context context) {
        context.startActivity(new Intent(context, FingerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger);
        ButterKnife.bind(this);
        ImmersionBar.with(this).transparentBar()
                .fitsSystemWindows(true).init();

        if(fingerFragment==null){
            fingerFragment=new FingerFragment();
        }
        fingerFragment.show(getSupportFragmentManager(),"LoginDialogFragment");


    }

    @OnClick(R.id.iv_finger)
    public void onViewClicked() {
        if(fingerFragment==null){
            fingerFragment=new FingerFragment();
        }
        fingerFragment.show(getSupportFragmentManager(),"LoginDialogFragment");
    }

}
