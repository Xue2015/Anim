package com.example.ja.arcmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by JA on 2015/7/22.
 */
public class PaintActivity extends Activity {

    /**������������*/
    private HuaBanView hbView;
    /**���ô�ϸ��Dialog*/
    private AlertDialog dialog;
    private View dialogView;
    private TextView shouWidth;
    private SeekBar widthSb;
    private int paintWidth;

    private void initView(){
        dialogView = getLayoutInflater().inflate(R.layout.dialog_width_set, null);
        shouWidth = (TextView) dialogView.findViewById(R.id.textView1);
        widthSb = (SeekBar) dialogView.findViewById(R.id.seekBar1);
        widthSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                shouWidth.setText("��ǰѡ�п�ȣ�"+(progress+1));
                paintWidth = progress+1;
            }
        });
        hbView = (HuaBanView)findViewById(R.id.huaBanView1);
        dialog = new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_info).setTitle("���û��ʿ��").
                setView(dialogView).setPositiveButton("ȷ��", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hbView.setPaintWidth(paintWidth);
            }
        }).setNegativeButton("ȡ��", null).create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ȥ��Ӧ�ñ�����
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paint_layout);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu colorSm = menu.addSubMenu(1, 1, 1, "ѡ�񻭱���ɫ");
        colorSm.add(2, 200, 200, "��ɫ");
        colorSm.add(2, 210, 210, "��ɫ");
        colorSm.add(2, 220, 220, "��ɫ");
        colorSm.add(2, 230, 230, "��ɫ");
        colorSm.add(2, 240, 240, "��ɫ");
        colorSm.add(2, 250, 250, "��ɫ");
        menu.add(1, 2, 2, "���û��ʴ�ϸ");
        SubMenu widthSm = menu.addSubMenu(1, 3, 3, "���û�����ʽ");
        widthSm.add(3, 300, 300, "��״����");
        widthSm.add(3, 301, 301, "��仭��");
        menu.add(1, 4, 4, "��ջ���");
        menu.add(1, 5, 5, "���滭��");
        menu.add(1, 6, 6, "�˳�Ӧ��");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int index = item.getItemId();
        switch(index){
            case 200:
                hbView.setColor(Color.RED);
                break;
            case 210:
                hbView.setColor(Color.GREEN);
                break;
            case 220:
                hbView.setColor(Color.BLUE);
                break;
            case 230:
                hbView.setColor(Color.MAGENTA);
                break;
            case 240:
                hbView.setColor(Color.YELLOW);
                break;
            case 250:
                hbView.setColor(Color.BLACK);
                break;
            case 2:
                dialog.show();
                break;
            case 300:
                hbView.setStyle(HuaBanView.PEN);
                break;
            case 301:
                hbView.setStyle(HuaBanView.PAIL);
                break;
            case 4:
                hbView.clearScreen();
                break;
            case 5:
                if(SaveViewUtil.saveScreen(hbView)){
                    Toast.makeText(this, "��ͼ�ɹ�", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "��ͼʧ�ܣ�����sdcard�Ƿ����", Toast.LENGTH_LONG).show();
                }
                break;
            case 6:
                finish();
                break;
        }
        return true;
    }


}