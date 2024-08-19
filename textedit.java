
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.FileDialog;

public class textedit extends JMenuBar implements ActionListener{
    //定义变量

    private String s = "";
    private String s1="";
    private String s2="";
    private Frame fr;
    //构造方法
    public textedit() {
        //从第48行到第94行都是向BorderLayout容器里添加组件
        JFrame fr = new JFrame("文本");
        TextArea ta = new TextArea();
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("文件(F)");
        m1.setMnemonic('F'); //设置快速访问符
        JMenu m2 = new JMenu("编辑(E)");
        m2.setMnemonic('E');
        JMenu m3 = new JMenu("帮助");

        JMenuItem mi1 =new JMenuItem("保存(S)",'S');
        mi1.setAccelerator(KeyStroke.getKeyStroke('S',ActionEvent.CTRL_MASK));
        JMenuItem mi2 = new JMenuItem("新建(N)",'N');
        mi2.setAccelerator(KeyStroke.getKeyStroke('N', ActionEvent.CTRL_MASK));
        JMenuItem mi3=new JMenuItem("退出(E)",'E');
        mi3.setAccelerator(KeyStroke.getKeyStroke('E',ActionEvent.CTRL_MASK));
        JMenuItem mi4 = new JMenuItem("展示帮助文档");
        JMenuItem mi5=new JMenuItem("打开(O)",'O');
        mi5.setAccelerator(KeyStroke.getKeyStroke('O',ActionEvent.CTRL_MASK));


        JMenuItem mi8 = new JMenuItem("复制(C)", 'C');
        JMenuItem mi9 = new JMenuItem("粘贴(V)",'V');
        JMenuItem mi10 = new JMenuItem("剪贴(T)",'T');
        JMenuItem mi11 = new JMenuItem("撤销(U)", 'E');
        JMenuItem mi12 = new JMenuItem("删除",'D');

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi5);
        m1.add(mi3);


        m2.add(mi8);
        m2.add(mi9);
        m2.add(mi10);
        m2.add(mi11);
        m2.add(mi12);

        m3.add(mi4);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
       
        ta.setEditable(true);
       
        ta.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                s = s + e.getKeyChar();
            }
        });
        //为菜单项mi1(保存)添加动作监听器
        mi1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("您正在保存文件，请稍后");
                try {
                    baocun();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
     
        mi2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.out.println("正在新建文本文件，请稍后");
                try {
                    xinjian();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }});
  
        mi3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            
                System.out.println("您已成功退出");
                System.exit(1);
            }
        });
     
        mi4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //向组件TextArea写
                ta.setText("这是个文本编辑器 \n\n这个文本编辑器的功能有:\n"+
                        "1.可以新建文本文件\n2..可以查看帮助内容\n3.可以删除\n"+
                        "4.可以打开文本文件\n5.可以保存文本文件"+
                        "\n\n注意事项\n新建的文本文件的位置在\"C:\\\\Users\\\\pu\\\\Desktop\"\n"
                        );
            }
        });

        mi5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                   
                    s2 = dakai();
                   
                    ta.setText(s2);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }});
        
        mi12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                String t1 = "";
                ta.setText(t1);
            }
        });
       fr.setJMenuBar(mb);
        fr.add(ta);
        fr.setVisible(true);
       
        fr.setBounds(100,100,400,400);
    }
    public static void main(String[] args) {
        textedit o = new textedit();
    }

    public String dakai() throws FileNotFoundException {
       
        FileDialog fd = new FileDialog(fr,"打开",FileDialog.LOAD);
        fd.setVisible(true);
        //如果你没有输入文件名
        if(fd.getFile()==null) {
            System.out.println("打开失败");
        }
        try {
            //获取你输入文件名的路径，并将它作为参数传递给流FileInputStream，把这个流作为参数传递给流InputStreamReader
            FileInputStream fis1 = new FileInputStream(fd.getDirectory()+fd.getFile());
            InputStreamReader isr1 = new InputStreamReader(fis1);
            //定义一个数组，这个数组存了你所输入那个文件的内容
            char[] cbuf = new char[10];
            isr1.read(cbuf, 0, 10);
            for(int j=0;j<10;j++) {
                s1 = s1 + cbuf[j];
            }
        }catch(Exception e) {
            System.out.println("打开异常");
        }
        //返回你所输入文件里的内容，并显示在TextArea里面
        return s1;
    }
    public void xinjian() throws IOException {
        //这里我选择在D://自己的软件这个目录下新建一个文本文件66.txt
        String s = "C:\\Users\\pu\\Desktop";
        String p = "99.txt";
        File f = new File(s,p);
        if(!f.exists()) {
            //新建文件的专属函数creatNewFile()
            f.createNewFile();
            System.out.println("新建成功");
            System.out.println("新建文本文件的位置："+f.getPath());
        }
    }
    //将你在TextArea里面所写的保存到一个文本文件里
    public void baocun() throws IOException,NullPointerException {
        FileDialog fd = new FileDialog(fr,"请输入你要保存的文件名",FileDialog.SAVE);
        System.out.println("你在TextArea里的输入为:"+s);
        fd.setVisible(true);
        if(fd.getDirectory()==null) {
            System.out.println("你没有选择保存位置");
        }
        try {
            FileOutputStream fos = new FileOutputStream(fd.getDirectory() + fd.getFile());
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(s);
            osw.flush();
            osw.close();
            fos.close();
        }catch(Exception e) {
            System.out.println("保存异常");
        }finally {
            System.out.println("您的内容保存到了:"+fd.getDirectory()+fd.getFile());
        }
    }
    public void actionPerformed(ActionEvent e) {
        System.exit(1);
    }

}

