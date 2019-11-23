package io.caoyu.wantodo.view.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.caoyu.wantodo.R;
import io.caoyu.wantodo.model.ToDoBean;
import io.caoyu.wantodo.repository.ToDoHelper;
import io.yugoal.lib_common_ui.base.BaseActivity;
import io.yugoal.lib_common_ui.utils.DateUtils;

/**
 * @user caoyu
 * @date 2019/11/13
 * 添加待办清单
 */
public class AddTodoActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.rt_content)
    EditText rtContent;

    private String date;
    private int type;
    private int priority;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        //初始化默认值
        date = DateUtils.getCurrentDate();
    }

    private void initView() {
        toolbar.inflateMenu(R.menu.add_todo);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_label:
//                        ToastUtils.showToast("功能开发中...");
                    showPriorityList();
                    break;
                case R.id.menu_date:
                    //日期选择，默认当天
                    showDatePicker();
                    break;
            }
            return true;
        });
        toolbar.setNavigationOnClickListener(v -> {
            try {
                ((InputMethodManager) Objects.requireNonNull(getSystemService(Context.INPUT_METHOD_SERVICE)))
                        .hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                onBackPressed();
            } finally {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (TextUtils.isEmpty(etTitle.getText())) {
            return;
        }
        ToDoBean toDoBean = new ToDoBean();
        toDoBean.setType(type);
        toDoBean.setContent(rtContent.getText().toString());
        toDoBean.setTitle(etTitle.getText().toString());
        toDoBean.setStatus(0);
        toDoBean.setDate(date);
        toDoBean.setDateStr("今天");
        toDoBean.setPriority(priority);
        ToDoHelper.addToDo(toDoBean);
    }

    /**
     * 优先级
     */
    private void showPriorityList() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pick_priority)
                .setItems(R.array.priority_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        priority += which;
                    }
                });
        builder.create().show();
    }


    /**
     * 日期选择
     */
    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = ++month;
                date = year + "-" + month + "-" + dayOfMonth;
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
