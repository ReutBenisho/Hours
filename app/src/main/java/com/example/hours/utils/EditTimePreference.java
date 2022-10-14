//package com.example.hours.utils;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.AttributeSet;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.preference.EditTextPreference;
//
//public class EditTimePreference extends EditTextPreference
//{
//    public EditTimePreference(Context ctx, AttributeSet attrs, int defStyle)
//    {
//        super(ctx, attrs, defStyle);
//    }
//
//    public EditTimePreference(Context ctx, AttributeSet attrs)
//    {
//        super(ctx, attrs);
//    }
//
//    public EditTimePreference(@NonNull Context context) {
//        super(context);
//    }
//
//    private class EditTextWatcher implements TextWatcher
//    {
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count){}
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int before, int count){}
//
//        @Override
//        public void afterTextChanged(Editable s)
//        {
//            onEditTextChanged();
//        }
//    }
//    EditTextWatcher m_watcher = new EditTextWatcher();
//
//    /**
//     * Return true in order to enable positive button or false to disable it.
//     */
//    protected boolean onCheckValue(String value)
//    {
//        return value.matches("^([01][0-9]|2[0-3]):([0-5][0-9])$");
//    }
//
//    protected void onEditTextChanged()
//    {
//        boolean enable = onCheckValue(getEditText().getText().toString());
//        Dialog dlg = getDialog();
//        if(dlg instanceof AlertDialog)
//        {
//            AlertDialog alertDlg = (AlertDialog)dlg;
//            Button btn = alertDlg.getButton(AlertDialog.BUTTON_POSITIVE);
//            btn.setEnabled(enable);
//        }
//    }
//
//    @Override
//    protected void showDialog(Bundle state)
//    {
//        super.showDialog(state);
//
//        getEditText().removeTextChangedListener(m_watcher);
//        getEditText().addTextChangedListener(m_watcher);
//        onEditTextChanged();
//    }
//}