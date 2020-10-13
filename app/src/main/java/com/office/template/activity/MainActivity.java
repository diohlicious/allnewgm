package com.office.template.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.office.template.R;
import com.office.template.base.data.TemplatePreference;
import com.office.template.base.function.TemplateFunction;
import com.office.template.base.implement.MainPresenterImp;
import com.office.template.base.permission.MultiPermissionActivity;
import com.office.template.base.presenter.MainPresenter;
import com.office.template.base.util.TemplateFragment;
import com.office.template.base.util.TemplateActivity;
import com.office.template.base.view.MainView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.office.template.base.contract.TemplateContract.REQUEST_CODE_MULTI_PERMISSION_ACTIVITY;
import static com.office.template.base.contract.TemplateContract.REQUEST_MULTI_PERMISSION;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */
public class MainActivity extends TemplateActivity implements TemplateFragment.FragmentInteractionListener, MainView {

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

    //----------------------------------------------------------------------
    @BindView(R.id.linear_home) LinearLayout linearHome;
    @BindView(R.id.tv_home) TextView tvHome;
    @BindView(R.id.iv_home) ImageView ivHome;
    @BindView(R.id.linear_home_second) LinearLayout linearHomeSecond;
    @BindView(R.id.tv_home_second) TextView tvHomeSecond;
    @BindView(R.id.iv_home_second) ImageView ivHomeSecond;
    @BindView(R.id.linear_home_three) LinearLayout linearHomeThree;
    @BindView(R.id.tv_home_three) TextView tvHomeThree;
    @BindView(R.id.iv_home_three) ImageView ivHomeThree;
    @BindView(R.id.linear_home_four) LinearLayout linearHomeFour;
    @BindView(R.id.tv_home_four) TextView tvHomeFour;
    @BindView(R.id.iv_home_four) ImageView ivHomeFour;

    private TemplatePreference templatePreference;
    private TemplateFunction templateFunction;
    private MainPresenter presenter;
    boolean doubleBackToExitPressedOnce = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        templatePreference = new TemplatePreference(this);
        templateFunction = new TemplateFunction(this);

        presenter = new MainPresenterImp(this, this);

        checkAndRequestPermissions();

        presenter.cannotReplaceFragmentHome();

    }

    @OnClick(R.id.linear_home)
    void homeClick(){
        presenter.cannotReplaceFragmentHome();
        ivHome.setImageResource(R.drawable.ic_home_enable);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryTextActive));
        ivHomeSecond.setImageResource(R.drawable.ic_home_disable);
        tvHomeSecond.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeThree.setImageResource(R.drawable.ic_home_disable);
        tvHomeThree.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeFour.setImageResource(R.drawable.ic_home_disable);
        tvHomeFour.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
    }


    @OnClick(R.id.linear_home_second)
    public void negotiationClickClick(){
        presenter.replaceFragmentHome();
        ivHome.setImageResource(R.drawable.ic_home_disable);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeSecond.setImageResource(R.drawable.ic_home_enable);
        tvHomeSecond.setTextColor(getResources().getColor(R.color.colorPrimaryTextActive));
        ivHomeThree.setImageResource(R.drawable.ic_home_disable);
        tvHomeThree.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeFour.setImageResource(R.drawable.ic_home_disable);
        tvHomeFour.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
    }

    @OnClick(R.id.linear_home_three)
    void productManagClick(){
        presenter.replaceFragmentHome();
        ivHome.setImageResource(R.drawable.ic_home_disable);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeSecond.setImageResource(R.drawable.ic_home_disable);
        tvHomeSecond.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeThree.setImageResource(R.drawable.ic_home_enable);
        tvHomeThree.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeFour.setImageResource(R.drawable.ic_home_disable);
        tvHomeFour.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
    }

    @OnClick(R.id.linear_home_four)
    void settingClick(){
        presenter.replaceFragmentHome();
        ivHome.setImageResource(R.drawable.ic_home_disable);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeSecond.setImageResource(R.drawable.ic_home_disable);
        tvHomeSecond.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeThree.setImageResource(R.drawable.ic_home_disable);
        tvHomeThree.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeFour.setImageResource(R.drawable.ic_home_enable);
        tvHomeFour.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.toast_please_click_back_again_to_exit), Toast.LENGTH_SHORT).show();
        presenter.cannotReplaceFragmentHome();
        ivHome.setImageResource(R.drawable.ic_home_enable);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryTextActive));
        ivHomeSecond.setImageResource(R.drawable.ic_home_disable);
        tvHomeSecond.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeThree.setImageResource(R.drawable.ic_home_disable);
        tvHomeThree.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        ivHomeFour.setImageResource(R.drawable.ic_home_disable);
        tvHomeFour.setTextColor(getResources().getColor(R.color.colorPrimaryTextInactive));
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);

    }

    @Override
    public void onFragmentInteraction(boolean isTabSolid, boolean isTabVisible) {}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE_MULTI_PERMISSION_ACTIVITY) {
            onPermissionActivityResult(resultCode);
        }
    }

    @Override
    public void onCameraPermissionResult(int[] grantResults) {
        boolean allGranted = grantResults.length == 5 &&
                grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[2] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[3] == android.content.pm.PackageManager.PERMISSION_GRANTED &&
                grantResults[4] == android.content.pm.PackageManager.PERMISSION_GRANTED;
        if (!allGranted) {
            Intent intent = new Intent(this, MultiPermissionActivity.class);
            startActivityForResult(intent, REQUEST_CODE_MULTI_PERMISSION_ACTIVITY);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_MULTI_PERMISSION) {
            onCameraPermissionResult(grantResults);
        }
    }

    @Override
    public void showDialogLoading(ProgressDialog progressDialog) {
        progressDialog.show();
    }

    @Override
    public void hideDialogLoading(ProgressDialog progressDialog) {
        progressDialog.dismiss();
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.page_fragment, fragment).commit();
    }

    @Override
    public void cannotReplaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.page_fragment, fragment).addToBackStack(null).commit();
    }

    @Override
    public void checkAndRequestPermissions() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            android.Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_MULTI_PERMISSION);
        }
    }

    @Override
    public void onPermissionActivityResult(int resultCode) {
        if(RESULT_CANCELED == resultCode) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if ((ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE)
                && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int[] scrcoords = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) Objects.requireNonNull(this.getSystemService(Context.INPUT_METHOD_SERVICE))).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
