package stu_info;
/**
 *@classname: NumberOnly
 *@author: shijinhai
 *@time: 2019年10月29日
*/
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
public class NumberOnly extends PlainDocument { //过滤非数字内容
    public NumberOnly() {
        super();
    }
    public void insertString(int offset, String str, AttributeSet attr)
            throws javax.swing.text.BadLocationException {
        if (str == null) {
            return;
        }
        char[] s = str.toCharArray();
        int length = 0;
        // 过滤非数字
        for (int i = 0; i < s.length; i++) {
            if ((s[i] >= '0') && (s[i] <= '9')) {
                s[length++] = s[i];
            }
        // 插入内容
        super.insertString(offset, new String(s, 0, length), attr);
        }
    }
}
