package com.example.hours.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.hours.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TimestampTextWatcher implements TextWatcher {
    private final EditText mEditText;
    private String current = "";
    private String hhmm = "HH:MM";

    public TimestampTextWatcher(EditText editTextView){
        mEditText = editTextView;
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int editTextId = mEditText.getId();
        String name = mEditText.getResources().getResourceName(editTextId);
        if(current == "")
            Log.d("onTextChanged", "view = " + name + " First entrance");

        Log.d("onTextChanged", "view = " + name + " checking if already equal to value...");
        if (!s.toString().equals(current)) {
            Log.d("onTextChanged", "view = " + name + " different (current is " + current + " and s is " + s.toString());
            String currentString = s.toString();
            int sel = currentString.length();
            //if(current != "") {
                String clean = s.toString().replaceAll(":", "");

                int cursorSelection = start;
                sel = cursorSelection;
                //Fix for pressing delete next to a forward slash
                //if (clean.equals(cleanC))
                //  sel--;
                if(start == 0 && (before == 5 || before == 0) && count == 5 && currentString.matches("^([0-9][0-9]):([0-9][0-9])$"))
                    current = currentString;
                else if (start >= 0) {
                    if(start <= 4) {
                        if (currentString.length() <= 5) {
                            //clean = clean + hhmm.substring(clean.length());
                            if(count == 0) {
                                if (!currentString.contains(":")) {
                                    start--;
                                    sel--;
                                }
                                clean = currentString.substring(0, start)
                                        + hhmm.charAt(start);
                                if (start < currentString.length() && currentString.contains(":"))
                                    clean += currentString.substring(start, currentString.length());
                                else if (start < currentString.length() && !currentString.contains(":"))
                                    clean += ":" + currentString.substring(start + 1, currentString.length());
                            }
                            else if(count > 0 && before == 0)
                            {
                                clean = currentString.substring(0, start)
                                        + hhmm.charAt(start);
                                if (start < currentString.length() && currentString.contains(":"))
                                    clean += currentString.substring(start, currentString.length());
                                else if (start < currentString.length() && !currentString.contains(":"))
                                    clean += ":" + currentString.substring(start + 1, currentString.length());
                            }
                            else if(before > 1 && count > 1)
                            {
                                if (!currentString.matches("^([0-9][0-9]):([0-9][0-9])$"))
                                {
                                    clean = currentString.substring(0, 2) + ":" + currentString.substring(3, 5);
                                }
                            }
                            else if(before == count)
                            {
                                clean = currentString;
                                sel = start + count;
                            }
                            else if(count < before)
                            {
                                if(start == 2) { //starting selection at :
                                    start++;
                                    sel++;
                                    currentString = currentString.substring(0, 2)
                                                    + ":"
                                                    + currentString.substring(2, currentString.length());
                                }
                                if(start + before == 2) //ending selection at :
                                {
                                    before--;
                                    count --;
                                }
                                clean = currentString.substring(0, start + 1)
                                        + hhmm.charAt(start + 1);
                                if (start + count < currentString.length())
                                    clean += currentString.substring(start + 1, currentString.length());

                                if (clean.length() != 5) {
                                    String contentClean = clean.substring(0, start + count);
                                    String contentHHMM = hhmm.substring(start + count, start + before);
                                    clean = contentClean
                                            + contentHHMM;
                                    if (start + count < currentString.length())
                                        clean += currentString.substring(start + count, currentString.length());
                                }
                                sel = start + 1;
//                                if (start < currentString.length() && currentString.contains(":"))
//                                    clean += currentString.substring(start, currentString.length());
//                                else if (start < currentString.length() && !currentString.contains(":"))
//                                    clean += ":" + currentString.substring(start + 1, currentString.length());
                            }

//                        if(!currentString.contains(":")) {
//                            //clean = currentString.substring(0, 1) + "H:" + currentString.substring(2, 4);
//
//                        }

                            //sel--;
                        } else if (before == 0 && count == 1){//if (before > 1 && before < count) {
                            //This part makes sure that when we finish entering numbers
                            //the date is correct, fixing it otherwise
                            clean = currentString.substring(0, start + 1)
                                    + currentString.substring(start + 2, currentString.length());
                            sel++;
                            if (!clean.contains(":")) {
                                clean = clean.substring(0, 2) + ":" + clean.substring(2, 4);
                                sel++;
                            }
                            //clean = String.format("%s%s",clean.substring(0, 2), clean.substring(2, 4));
                        }
                        else if(before > 1 && count > 1)
                        {
                            if (!currentString.matches("^([0-9][0-9]):([0-9][0-9])$"))
                            {
                                clean = currentString.substring(0, 2) + ":" + currentString.substring(3, 5);
                            }
                        }


//            clean = String.format("%s:%s", clean.substring(0, 2),
//                    clean.substring(2, 4));

                        sel = sel < 0 ? 0 : sel;

                        current = clean;
                    }
                }

//            }
//            else
//            {
//                current = currentString;
//
//            }

            Log.d("onTextChanged", "view = " + name + " set value to " + current);
            mEditText.setText(current);
            mEditText.setSelection(sel < current.length() ? sel : current.length());

            if(mEditText instanceof TextInputEditText)
            {
                TextInputLayout textLayout = (TextInputLayout) mEditText.getParent().getParent();
                if (current.matches("^([01][0-9]|2[0-3]):([0-5][0-9])$"))
                {
                    textLayout.setError("");
                    ListenerManager.NotifyListeners(ListenerManager.ListenerType.INFO_LABELS);
                }
                else
                    textLayout.setError(App.getStr(R.string.invalidTime));
            }
            else {
                if (current.matches("^([01][0-9]|2[0-3]):([0-5][0-9])$"))
                {
                    mEditText.setTextColor(mEditText.getResources().getColor(R.color.settings_text_primary));
                }
                else
                   mEditText.setTextColor(mEditText.getResources().getColor(R.color.red));
            }

        }
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
