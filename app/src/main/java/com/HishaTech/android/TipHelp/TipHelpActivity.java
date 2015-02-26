package com.HishaTech.android.TipHelp;

import java.text.DecimalFormat;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class TipHelpActivity extends Activity {

    /** Called when the activity is first created. */
    private EditText txtBillAmount, txtTipAmount, txtTotal, txtGroupSize;
    private TextView lblTipAmount, lblTotal;
    private Spinner ddlTipPercent;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0,
            btnDecimal, btnEnter, btnClr, btnDel;
    private RadioButton rdoone, rdotwo, rdomore;
    private RadioGroup rdoGroup;
    private String sBillAmount, sGroupSize, sTipAmount, sTipAmountGroup,
            sTotal, sTotalGroup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Access various widgets in Application.
        txtBillAmount = (EditText) findViewById(R.id.txtBillAmount);
        lblTipAmount = (TextView) findViewById(R.id.lblTipAmount);
        txtTipAmount = (EditText) findViewById(R.id.txtTipAmount);
        lblTotal = (TextView) findViewById(R.id.lblTotal);
        txtTotal = (EditText) findViewById(R.id.txtTotal);
        ddlTipPercent = (Spinner) findViewById(R.id.ddlTipPercent);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        btnDecimal = (Button) findViewById(R.id.btnDecimal);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnClr = (Button) findViewById(R.id.btnClr);
        txtGroupSize = (EditText) findViewById(R.id.txtGroupSize);
        rdoGroup = (RadioGroup) findViewById(R.id.rdoGroup);
        rdoone = (RadioButton) findViewById(R.id.rdoone);
        rdotwo = (RadioButton) findViewById(R.id.rdotwo);
        rdomore = (RadioButton) findViewById(R.id.rdomore);
        sTipAmount = getResources().getString(R.string.lblTipAmount);
        sTipAmountGroup = getResources().getString(R.string.lblTipAmountGroup);
        sTotal = getResources().getString(R.string.lblTotal);
        sTotalGroup = getResources().getString(R.string.lblTotalGroup);

        // Get Shared Preferences.
        SharedPreferences _sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        String defaultTipPercent = _sharedPreferences.getString(
                "default_percent", "15");
        String defaultGroupSize = _sharedPreferences.getString(
                "default_groupsize", "1");
        txtBillAmount.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtTipAmount.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtTotal.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        txtGroupSize.setInputType(InputType.TYPE_CLASS_NUMBER);

        // Disable the virtual keyboard from all EditTexts
        txtBillAmount.setInputType(0);
        txtTipAmount.setInputType(0);
        txtTotal.setInputType(0);
        txtGroupSize.setInputType(0);

        // Convert string of preference to Integer.
        Integer intTipPercent = Integer.parseInt(defaultTipPercent);
        Integer intGroupSize = Integer.parseInt(defaultGroupSize);

        // Set Tip Percent to what is set in preferences.
        switch (intTipPercent) {
            case 5:
                ddlTipPercent.setSelection(0);
                break;
            case 10:
                ddlTipPercent.setSelection(1);
                break;
            case 15:
                ddlTipPercent.setSelection(2);
                break;
            case 20:
                ddlTipPercent.setSelection(3);
                break;
        }

        // Set Group Size based on preferences and disable/enable the Group Size
        // text box.
        switch (intGroupSize) {
            case 1:
                rdoone.setChecked(true);
                txtGroupSize.setEnabled(false);
                txtGroupSize.setBackgroundColor(0xff000000);
                lblTipAmount.setText(sTipAmount);
                lblTotal.setText(sTotal);
                break;
            case 2:
                rdotwo.setChecked(true);
                txtGroupSize.setText("2");
                txtGroupSize.setEnabled(false);
                txtGroupSize.setBackgroundColor(0xff000000);
                lblTipAmount.setText(sTipAmountGroup);
                lblTotal.setText(sTotalGroup);
                break;
            case 3:
                rdomore.setChecked(true);
                txtGroupSize.setEnabled(true);
                txtGroupSize.setBackgroundColor(0xffffffff);
                lblTipAmount.setText(sTipAmountGroup);
                lblTotal.setText(sTotalGroup);
                break;
        }

        // Set string blank.
        sBillAmount = "";

        // Set Focus on load
        txtBillAmount.requestFocus();

        // Disable Continue button on load.
        btnEnter.setEnabled(false);

        // Add KeyListener to enable Continue button.
        txtBillAmount.setOnKeyListener(mKeyListener);
        txtGroupSize.setOnKeyListener(mKeyListener);

        // Add OnClickListener to the buttons
        btn1.setOnClickListener(mClickListener);
        btn2.setOnClickListener(mClickListener);
        btn3.setOnClickListener(mClickListener);
        btn4.setOnClickListener(mClickListener);
        btn5.setOnClickListener(mClickListener);
        btn6.setOnClickListener(mClickListener);
        btn7.setOnClickListener(mClickListener);
        btn8.setOnClickListener(mClickListener);
        btn9.setOnClickListener(mClickListener);
        btn0.setOnClickListener(mClickListener);
        btnDecimal.setOnClickListener(mClickListener);
        btnDel.setOnClickListener(mClickListener);
        btnEnter.setOnClickListener(mClickListener);
        btnClr.setOnClickListener(mClickListener);

        rdoGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int checkedId) {
                if (checkedId == R.id.rdoone) {
                    txtGroupSize.setEnabled(false);
                    txtGroupSize.setBackgroundColor(0xff000000);
                    lblTipAmount.setText(sTipAmount);
                    lblTotal.setText(sTotal);
                }
                if (checkedId == R.id.rdotwo) {
                    txtGroupSize.setText("2");
                    txtGroupSize.setEnabled(false);
                    txtGroupSize.setBackgroundColor(0xff000000);
                    lblTipAmount.setText(sTipAmountGroup);
                    lblTotal.setText(sTotalGroup);
                }
                if (checkedId == R.id.rdomore) {
                    txtGroupSize.setEnabled(true);
                    txtGroupSize.setBackgroundColor(0xffffffff);
                    txtGroupSize.requestFocus();
                    lblTipAmount.setText(sTipAmountGroup);
                    lblTotal.setText(sTotalGroup);
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Options:
                Intent Prefintent = new Intent(this, preferenceActivity.class);
                this.startActivity(Prefintent);
                break;
            case R.id.About:
                Intent Aboutintent = new Intent(this, AboutActivity.class);
                this.startActivity(Aboutintent);
                break;
        }
        return false; // should never happen
    }

    private OnKeyListener mKeyListener = new OnKeyListener() {
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            switch (v.getId()) {
                case R.id.txtBillAmount:
                    if (rdomore.isChecked()) {
                        btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                && txtGroupSize.getText().length() > 0);
                        break;
                    } else {
                        btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                        break;
                    }
                case R.id.txtGroupSize:
                    if (rdomore.isChecked()) {
                        btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                && txtGroupSize.getText().length() > 0);
                        break;
                    } else {
                        btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                        break;
                    }
            }
            return false;
        }
    };

    private OnClickListener mClickListener = new OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnEnter:
                    Calculate();
                    break;
                case R.id.btnClr:
                    ClearText();
                    break;
                case R.id.btn1:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + "1";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        sGroupSize = sGroupSize + "1";
                        txtGroupSize.setText(sGroupSize);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                case R.id.btn2:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + "2";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        sGroupSize = sGroupSize + "2";
                        txtGroupSize.setText(sGroupSize);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                case R.id.btn3:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + "3";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        sGroupSize = sGroupSize + "3";
                        txtGroupSize.setText(sGroupSize);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                case R.id.btn4:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + "4";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        sGroupSize = sGroupSize + "4";
                        txtGroupSize.setText(sGroupSize);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                case R.id.btn5:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + "5";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        sGroupSize = sGroupSize + "5";
                        txtGroupSize.setText(sGroupSize);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                case R.id.btn6:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + "6";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        sGroupSize = sGroupSize + "6";
                        txtGroupSize.setText(sGroupSize);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                case R.id.btn7:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + "7";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        sGroupSize = sGroupSize + "7";
                        txtGroupSize.setText(sGroupSize);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                case R.id.btn8:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + "8";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        sGroupSize = sGroupSize + "8";
                        txtGroupSize.setText(sGroupSize);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                case R.id.btn9:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + "9";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        sGroupSize = sGroupSize + "9";
                        txtGroupSize.setText(sGroupSize);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                case R.id.btn0:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + "0";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        sGroupSize = sGroupSize + "0";
                        txtGroupSize.setText(sGroupSize);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                case R.id.btnDecimal:
                    if (sBillAmount.contains(".")) {
                    } else {
                        sBillAmount = txtBillAmount.getText().toString();
                        sBillAmount = sBillAmount + ".";
                        txtBillAmount.setText(sBillAmount);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
                    break;
                case R.id.btnDel:
                    if (txtBillAmount.isFocused()) {
                        sBillAmount = txtBillAmount.getText().toString();
                        String sBillDel = sBillAmount.substring(0,
                                sBillAmount.length() - 1);
                        txtBillAmount.setText(sBillDel);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    } else {
                        sGroupSize = txtGroupSize.getText().toString();
                        String sGroupDel = sGroupSize.substring(0,
                                sGroupSize.length() - 1);
                        txtGroupSize.setText(sGroupDel);
                        if (rdomore.isChecked()) {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0
                                    && txtGroupSize.getText().length() > 0);
                            break;
                        } else {
                            btnEnter.setEnabled(txtBillAmount.getText().length() > 0);
                            break;
                        }
                    }
            }
        }
    };
    private void Calculate() {
        Double BillAmount = Double.parseDouble(txtBillAmount.getText()
                .toString());
        int pos = ddlTipPercent.getSelectedItemPosition();
        String TipPercentValue = getResources().getStringArray(
                R.array.TipPercentValues)[pos];
        Double TipPercent = Double.parseDouble(TipPercentValue);
        boolean isError = false;
        if (BillAmount < 1.0) {
            Toast toast = Toast
                    .makeText(this, "Enter Valid Bill Amount.", 2000);
            toast.setGravity(Gravity.TOP, -30, 50);
            toast.show();
            isError = true;
        }
        if (!isError) {
// Calculate Amounts.
            Double tipAmount = ((BillAmount * TipPercent) / 100);
            Double total = BillAmount + tipAmount;
            if (rdomore.isChecked() || rdotwo.isChecked()) {
                Double GrpSize = Double.parseDouble(txtGroupSize.getText()
                        .toString());
                Double grouptip = tipAmount / GrpSize;
                Double grouptotal = total / GrpSize;
                if (grouptip.toString().endsWith(".0")) {
                    String[] FixTip = grouptip.toString().split("[.]");
                    int TotalTipFront = Integer.parseInt(FixTip[0]);
                    String FixedTip = TotalTipFront + ".00";
                    txtTipAmount.setText(FixedTip);
                } else {
                    Double TipAmountClean = TwoDecimalRound(grouptip);
                    txtTipAmount.setText(TipAmountClean.toString());
                }
                if (grouptotal.toString().endsWith(".0")) {
                    String[] FixTotal = grouptotal.toString().split("[.]");
                    int TotalFront = Integer.parseInt(FixTotal[0]);
                    String FixedTotal = TotalFront + ".00";
                    txtTotal.setText(FixedTotal);
                } else {
                    Double TotalClean = TwoDecimalRound(grouptotal);
                    txtTotal.setText(TotalClean.toString());
                }
            } else {
                if (tipAmount.toString().endsWith(".0")) {
                    String[] FixTip = tipAmount.toString().split("[.]");
                    int TotalTipFront = Integer.parseInt(FixTip[0]);
                    String FixedTip = TotalTipFront + ".00";
                    txtTipAmount.setText(FixedTip);
                } else {
                    Double TipAmountClean = TwoDecimalRound(tipAmount);
                    txtTipAmount.setText(TipAmountClean.toString());
                }
                if (total.toString().endsWith(".0")) {
                    String[] FixTotal = total.toString().split("[.]");
                    int TotalFront = Integer.parseInt(FixTotal[0]);
                    String FixedTotal = TotalFront + ".00";
                    txtTotal.setText(FixedTotal);
                } else {
                    Double TotalClean = TwoDecimalRound(total);
                    txtTotal.setText(TotalClean.toString());
                }
            }
        }
    }
    private double TwoDecimalRound(double d) {
        try {
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            return Double.valueOf(twoDForm.format(d));
        } catch (NumberFormatException ex) {
// TODO: handle error
        }
        return 0;
    }
    private void ClearText() {
// Clear fields.
        sBillAmount = "";
        sGroupSize = "";
        txtBillAmount.setText("");
        txtGroupSize.setText("");
        txtTipAmount.setText("");
        txtTotal.setText("");
// Disable Continue button
        btnEnter.setEnabled(false);
// Update Tip Percent to preference in case of change.
        SharedPreferences _sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        String defaultTipPercent = _sharedPreferences.getString(
                "default_percent", "15");
        String defaultGroupSize = _sharedPreferences.getString(
                "default_groupsize", "1");
// Convert string of preference to Integer.
        Integer intTipPercent = Integer.parseInt(defaultTipPercent);
        Integer intGroupSize = Integer.parseInt(defaultGroupSize);
// Set Tip Percent to what is set in preferences.
        switch (intTipPercent) {
            case 5:
                ddlTipPercent.setSelection(0);
                break;
            case 10:
                ddlTipPercent.setSelection(1);
                break;
            case 15:
                ddlTipPercent.setSelection(2);
                break;
            case 20:
                ddlTipPercent.setSelection(3);
                break;
        }
// Set Group Size based on preferences and disable/enable the Group Size
// text box.
        switch (intGroupSize) {
            case 1:
                rdoone.setChecked(true);
                txtGroupSize.setEnabled(false);
                txtGroupSize.setBackgroundColor(0xff000000);
                lblTipAmount.setText(sTipAmount);
                lblTotal.setText(sTotal);
                break;
            case 2:
                rdotwo.setChecked(true);
                txtGroupSize.setText("2");
                txtGroupSize.setEnabled(false);
                txtGroupSize.setBackgroundColor(0xff000000);
                lblTipAmount.setText(sTipAmountGroup);
                lblTotal.setText(sTotalGroup);
                break;
            case 3:
                rdomore.setChecked(true);
                txtGroupSize.setEnabled(true);
                txtGroupSize.setBackgroundColor(0xffffffff);
                lblTipAmount.setText(sTipAmountGroup);
                lblTotal.setText(sTotalGroup);
                break;
        }
// Focus Bill Amount field.
        txtBillAmount.requestFocus();
    }
}