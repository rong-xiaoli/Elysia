package top.rongxiaoli.plugins.DailySign;

import net.mamoe.mirai.utils.MiraiLogger;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DailySignString {
    private final MiraiLogger LOGGER = MiraiLogger.Factory.INSTANCE.create(DailySignString.class);

    /**
     * Get a random string.
     *
     * @param Year        Year.
     * @param Month       Month.
     * @param Day         Day.
     * @param Week        Day of week.
     * @param Hour        Hour.
     * @param Minute      Minute.
     * @param Second      Second. Used for Jan 1st.
     * @param Millisecond Millisecond. Used for Jan 1st.
     * @return NewRandom string.
     */
    public String GetRandomString(int Year, int Month, int Day, DayOfWeek Week, int Hour, int Minute, int Second, int Millisecond) {
        //Variables declaration.
        String YearBasedString;
        String MonthBasedString;
        String DayBasedString;
        String WeekBasedString;
        String HourBasedString;
        String OtherString;
        String MixedString = "";

        int StringSelection;

        Random random = new Random();

        //Year only.
        YearBasedString = "今年是";
        switch (Year % 12) {
            case 0:
                YearBasedString += "猴年呢~";
                break;
            case 1:
                YearBasedString += "鸡年呢~";
                break;
            case 2:
                YearBasedString += "狗年呢~";
                break;
            case 3:
                YearBasedString += "猪年呢~";
                break;
            case 4:
                YearBasedString += "鼠年呢~";
                break;
            case 5:
                YearBasedString += "牛年呢~";
                break;
            case 6:
                YearBasedString += "虎年呢~";
                break;
            case 7:
                YearBasedString += "兔年呢~";
                break;
            case 8:
                YearBasedString += "龙年呢~";
                break;
            case 9:
                YearBasedString += "蛇年呢~";
                break;
            case 10:
                YearBasedString += "马年呢~";
                break;
            case 11:
                YearBasedString += "羊年呢~";
                break;
        }
        //Holiday region start.
        //MixedString.
        if (Month == 1 && Day >= 1 && Day <= 3) {
            MixedString = "元旦快乐！";
        }
        if (Month == 1 && Day == 1 && Hour == 0 && Minute == 0) {
            MixedString = "真准时呢~元旦快乐哦~";
        }
        if (Month == 1 && Day == 1 && Hour == 0 && Minute == 0 && Second == 0 && Millisecond == 0) {
            MixedString = "怎么做到这么准时的？？新年快乐~";
        }
        if (Month == 2 && Day == 14) {
            MixedString = "祝福天下有情人~";
        }
        if (Month == 3 && Day == 8) {
            MixedString = "祝各位妇女们节日快乐~";
        }
        if (Month == 4 && Day == 1) {
            MixedString = "Never gonna give you up~";
        }
        if (Month == 5 && Day == 1) {
            MixedString = "Debout, les damnés de la terre~";
        }
        if (Month == 5 && Day == 4) {
            MixedString = "我们是初升的太阳， 用生命点燃未来！";
        }
        if (Month == 5 && Day >= 8 && Day <= 14 && Week.getValue() == 7) {
            MixedString = "母爱的伟大！";
        }
        if (Month == 6 && Day == 1) {
            MixedString = "给祖国未来花献花~";
        }
        if (Month == 6 && Day >= 15 && Day <= 21 && Week.getValue() == 7) {
            MixedString = "给爸爸揉揉肩吧~";
        }
        if (Month == 7 && Day == 1) {
            MixedString = "满腔的热血已经沸腾, 要为真理而斗争! ";
        }
        if (Month == 8 && Day == 1) {
            MixedString = "最可爱的人们~";
        }
        if (Month == 8 && Day >= 24) {
            MixedString = "暑假作业做完了吗？（恶魔低语）";
        }
        if (Month == 8 && Day == 31) {
            MixedString = "返校了吗？";
        }
        if (Month == 9 && Day == 1) {
            MixedString = "开学快乐！";
        }
        if (Month == 10 && Day >= 1 && Day <= 7) {
            MixedString = "国庆快乐！";
        }
        if (Month == 10 && Day == 31 && Hour >= 18) {
            MixedString = "Trick or treat~";
        }
        if (Month == 11 && Day == 11) {
            MixedString = "今天，是超市特价日啊！！！";
        }
        if (Month == 12 && Day == 24 && Hour >= 18) {
            MixedString = "Silent night, holy night~";
        }
        if (Month == 12 && Day == 25 && Hour <= 12) {
            MixedString = "Ho ho ho! Merry Christmas! ";
        }
        if (Month == 12 && Day == 31) {
            MixedString = "年末啦~一年以来辛苦了~";
        }
        //Month only.
        switch (Month) {
            case 1:
                MonthBasedString = "新的一年，新的开始";
                break;
            case 2:
                MonthBasedString = "二月休假了吗？";
                break;
            case 3:
                MonthBasedString = "常去户外走走，看看风景吧~";
                break;
            case 4:
                MonthBasedString = "鼻炎患者记得戴好口罩~";
                break;
            case 5:
                MonthBasedString = "出门记得带雨伞哦~";
                break;
            case 6:
                MonthBasedString = "户外记得避暑啊~";
                break;
            case 7:
                MonthBasedString = "放暑假了吗？";
                break;
            case 8:
                MonthBasedString = "吃点雪糕吧~";
                break;
            case 9:
                MonthBasedString = "开学季啦~";
                break;
            case 10:
                MonthBasedString = "冷要记得添衣服啊~";
                break;
            case 11:
                MonthBasedString = "好冷哦~";
                break;
            case 12:
                MonthBasedString = "年底啦！";
                break;
            default:
                MonthBasedString = "美好的一天~";
                LOGGER.warning("Unexpected value: Month = " + Month);
                break;
        }
        //Day of week only.
        switch (Week) {
            case MONDAY:
                WeekBasedString = "一周之始~今天也要加油呀~";
                break;
            case TUESDAY:
                WeekBasedString = "昨天过得怎样~";
                break;
            case WEDNESDAY:
                WeekBasedString = "周中了哦~";
                break;
            case THURSDAY:
                WeekBasedString = "今天，是疯狂星期四啊！v我50！！";
                break;
            case FRIDAY:
                WeekBasedString = "周末要来啦！";
                break;
            case SATURDAY:
                WeekBasedString = "周末开始了呢~宅在家里做什么好呢~";
                break;
            case SUNDAY:
                WeekBasedString = "周末结束了呢~准备上班咯~";
                break;
            default:
                WeekBasedString = "美好的一天~";
                LOGGER.warning("Unexpected value: Week = " + Week);
                break;
        }
        //Day only.
        switch (Day) {
            case 30:
            case 31:
                DayBasedString = "月末了~工资发了吗？";
                break;
            case 1:
                DayBasedString = "月初了~加油工作呀~";
                break;
            default:
                DayBasedString = "今天也要加油呀~";
                break;
        }
        //Hour only.
        switch (Hour) {
            case 0:
            case 1:
            case 2:
                HourBasedString = "夜深了，还不睡吗~";
                break;
            case 3:
            case 4:
                HourBasedString = "您完全不睡觉的是吗（震惊）";
                break;
            case 5:
                HourBasedString = "早起的鸟儿有虫吃~你也太早了吧~";
                break;
            case 6:
                HourBasedString = "早安~一日之计在于晨";
                break;
            case 7:
                HourBasedString = "早上好~睡得怎么样？";
                break;
            case 8:
                HourBasedString = "还不起来要迟到了哦？";
                break;
            case 9:
                HourBasedString = "要安心工作了呢！";
                break;
            case 10:
                HourBasedString = "记得喝口水啊~";
                break;
            case 11:
                HourBasedString = "午饭吃什么呢~（思索）";
                break;
            case 12:
                HourBasedString = "中饭时间到~";
                break;
            case 13:
                HourBasedString = "呜呜呜~我要睡会儿嘛~";
                break;
            case 14:
                HourBasedString = "继续下午的工作吧~";
                break;
            case 15:
                HourBasedString = "三点几嚟,饮茶先啦~";
                break;
            case 16:
                HourBasedString = "摸鱼摸鱼~";
                break;
            case 17:
                HourBasedString = "朝九晚五的同志们，下班咯~";
                break;
            case 18:
                HourBasedString = "晚饭吃啥好呢~";
                break;
            case 19:
            case 20:
                HourBasedString = "追番追剧咯~";
                break;
            case 21:
                HourBasedString = "喝杯牛奶吧~";
                break;
            case 22:
                HourBasedString = "洗洗睡吧~";
                break;
            case 23:
                HourBasedString = "夜深了，要陪你睡觉吗？";
                break;
            default:
                HourBasedString = "美好的一天~";
                LOGGER.warning("Unexpected value: Hour = " + Hour);
                break;
        }

        switch (random.nextInt(0, 6)) {
            case 0:
                OtherString = "QwQ";
                break;
            case 1:
                OtherString = "awa";
                break;
            case 2:
                OtherString = "QAQ";
                break;
            case 3:
                OtherString = "你在干什么呢~";
                break;
            case 4:
                OtherString = "看着你忙碌~";
                break;
            default:
                LOGGER.warning("Unexpected value: (nextInt(6))");
            case 5:
                OtherString = "C#是世界上最美的语言！";
                break;
        }

        //Process start.
        if (!MixedString.equals("")) {
            return MixedString;
        }
        StringSelection = random.nextInt(1, 6);
        switch (StringSelection) {
            case 1:
                return YearBasedString;
            case 2:
                return MonthBasedString;
            case 3:
                return WeekBasedString;
            case 4:
                return DayBasedString;
            case 6:
                return OtherString;
            case 5:
            default:
                return HourBasedString;
        }
    }

    /**
     * Generate a sign in content for a friend.
     *
     * @param presentTime    Present time.
     * @param signInPosition Sign in position.
     * @return Sign in message content.
     */
    public String FriendString(LocalDateTime presentTime, long signInPosition) {
        StringBuilder friendStringBuilder = new StringBuilder();
        int year = presentTime.getYear(),
                month = presentTime.getMonthValue(),
                day = presentTime.getDayOfMonth(),
                hour = presentTime.getHour(),
                minute = presentTime.getMinute(),
                second = presentTime.getSecond(),
                millisecond = presentTime.getNano();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        friendStringBuilder.append("今天是").append(year).append("年").append(month).append("月").append(day).append("日").append("\n");
        friendStringBuilder.append("现在是").append(presentTime.format(formatter)).append("\n");
        friendStringBuilder.append("本次打卡位次为：").append(signInPosition).append("\n");
        friendStringBuilder.append(GetRandomString(year, month, day, presentTime.getDayOfWeek(), hour, minute, second, millisecond));
        return friendStringBuilder.toString();
    }
}
