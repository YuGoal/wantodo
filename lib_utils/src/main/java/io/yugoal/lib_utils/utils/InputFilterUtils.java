package io.yugoal.lib_utils.utils;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 输入框表情过滤
 */
public class InputFilterUtils {

    private static InputFilter emojiFilter;

    public static InputFilter[] getEmojiFilter() {
        if (null == emojiFilter) {
            emojiFilter = new InputFilter() {
                Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    Matcher emojiMatcher = emoji.matcher(source);
                    if (emojiMatcher.find()) {
                        ToastUtils.showToast("不支持输入表情");
                        return "";
                    }
                    return null;
                }
            };
        }
        return new InputFilter[]{emojiFilter};
    }

}
