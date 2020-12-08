package com.sip.grosirmobil.fragment.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.shuhart.stepview.StepView;
import com.sip.grosirmobil.R;
import com.sip.grosirmobil.activity.CodeOtpFragment;
import com.sip.grosirmobil.activity.RegisterDataActivity;
import com.sip.grosirmobil.adapter.QuestionAdapter;
import com.sip.grosirmobil.base.data.GrosirMobilPreference;
import com.sip.grosirmobil.base.function.GrosirMobilFunction;
import com.sip.grosirmobil.base.log.GrosirMobilLog;
import com.sip.grosirmobil.cloud.config.response.question.QuestionResponse;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sip.grosirmobil.base.GrosirMobilApp.getApiGrosirMobil;

public class QuestionFragment extends Fragment {

    private GrosirMobilPreference grosirMobilPreference;
    private GrosirMobilFunction grosirMobilFunction;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.step_view) StepView stepView;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_question_1) TextInputEditText etQuestion1;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_question_2) TextInputEditText etQuestion2;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_question_3) TextInputEditText etQuestion3;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_question_4) TextInputEditText etQuestion4;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_question_5) TextInputEditText etQuestion5;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.relative_dialog) RelativeLayout relativeDialog;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_choose) RecyclerView rvChoose;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.progress_circular) ProgressBar progressBar;

    public static QuestionFragment newInstance(int page, String title) {
        QuestionFragment fragmentFirst = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        ButterKnife.bind(this, view);

        grosirMobilPreference = new GrosirMobilPreference(getActivity());
        grosirMobilFunction = new GrosirMobilFunction(getActivity());

        stepView.go(4, true);
        return view;
    }

    private void showDialogChoose(){
        relativeDialog.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager layoutManagerChoose = new LinearLayoutManager(getActivity());
        rvChoose.setLayoutManager(layoutManagerChoose);
        rvChoose.setNestedScrollingEnabled(false);
        rvChoose.setAdapter(null);
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.linear_content_dialog)
    void linearContentDialogClick(){
        relativeDialog.setVisibility(View.VISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.relative_dialog)
    void relativeDialogClick(){
        relativeDialog.setVisibility(View.GONE);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_question_1)
    void etQuestion1Click(){
        showProgressBar();
        showDialogChoose();
        final Call<QuestionResponse> questionOneApi = getApiGrosirMobil().questionOneApi();
        questionOneApi.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            QuestionAdapter questionAdapter = new QuestionAdapter(response.body().getData(), dataQuestionResponse -> {
                                etQuestion1.setText(dataQuestionResponse.getName());
                                etQuestion1.setTag(dataQuestionResponse.getCode());
                                relativeDialogClick();
                            });
                            rvChoose.setAdapter(questionAdapter);
                            questionAdapter.notifyDataSetChanged();
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "GET Question", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                hideProgressBar();
                grosirMobilFunction.showMessage(getActivity(), "GET Question", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_question_2)
    void etQuestion2Click(){
        showProgressBar();
        showDialogChoose();
        final Call<QuestionResponse> questionTwoApi = getApiGrosirMobil().questionTwoApi();
        questionTwoApi.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            QuestionAdapter questionAdapter = new QuestionAdapter(response.body().getData(), dataQuestionResponse -> {
                                etQuestion2.setText(dataQuestionResponse.getName());
                                etQuestion2.setTag(dataQuestionResponse.getCode());
                                relativeDialogClick();
                            });
                            rvChoose.setAdapter(questionAdapter);
                            questionAdapter.notifyDataSetChanged();
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "GET Question", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                hideProgressBar();
                grosirMobilFunction.showMessage(getActivity(), "GET Question", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_question_3)
    void etQuestion3Click(){
        showProgressBar();
        showDialogChoose();
        final Call<QuestionResponse> questionThreeApi = getApiGrosirMobil().questionThreeApi();
        questionThreeApi.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            QuestionAdapter questionAdapter = new QuestionAdapter(response.body().getData(), dataQuestionResponse -> {
                                etQuestion3.setText(dataQuestionResponse.getName());
                                etQuestion3.setTag(dataQuestionResponse.getCode());
                                relativeDialogClick();
                            });
                            rvChoose.setAdapter(questionAdapter);
                            questionAdapter.notifyDataSetChanged();
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "GET Question", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                hideProgressBar();
                grosirMobilFunction.showMessage(getActivity(), "GET Question", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_question_4)
    void etQuestion4Click(){
        showProgressBar();
        showDialogChoose();
        final Call<QuestionResponse> questionFourApi = getApiGrosirMobil().questionFourApi();
        questionFourApi.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            QuestionAdapter questionAdapter = new QuestionAdapter(response.body().getData(), dataQuestionResponse -> {
                                etQuestion4.setText(dataQuestionResponse.getName());
                                etQuestion4.setTag(dataQuestionResponse.getCode());
                                relativeDialogClick();
                            });
                            rvChoose.setAdapter(questionAdapter);
                            questionAdapter.notifyDataSetChanged();
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "GET Question", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                hideProgressBar();
                grosirMobilFunction.showMessage(getActivity(), "GET Question", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.et_question_5)
    void etQuestion5Click(){
        showProgressBar();
        showDialogChoose();
        final Call<QuestionResponse> questionFiveApi = getApiGrosirMobil().questionFiveApi();
        questionFiveApi.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    try {
                        if(response.body().getMessage().equals("success")){
                            QuestionAdapter questionAdapter = new QuestionAdapter(response.body().getData(), dataQuestionResponse -> {
                                etQuestion5.setText(dataQuestionResponse.getName());
                                etQuestion5.setTag(dataQuestionResponse.getCode());
                                relativeDialogClick();
                            });
                            rvChoose.setAdapter(questionAdapter);
                            questionAdapter.notifyDataSetChanged();
                        }else {
                            grosirMobilFunction.showMessage(getActivity(), "GET Question", response.body().getMessage());
                        }
                    }catch (Exception e){
                        GrosirMobilLog.printStackTrace(e);
                    }
                }else {
                    try {
                        grosirMobilFunction.showMessage(getActivity(), getString(R.string.base_null_error_title), response.errorBody().string());
                    } catch (IOException e) {
                        GrosirMobilLog.printStackTrace(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {
                hideProgressBar();
                grosirMobilFunction.showMessage(getActivity(), "GET Question", getString(R.string.base_null_server));
                GrosirMobilLog.printStackTrace(t);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_back)
    void ivBackClick(){
        ((RegisterDataActivity)getActivity()).setFragment();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.btn_save)
    void btnSaveClick(){
        ((RegisterDataActivity)getActivity()).replaceFragment(new CodeOtpFragment());
    }
}