package test;

import java.util.List;

import dao.ChsDao;
import model.Chs;

public class ChsDaoTest {
    public static void main(String[] args) {
        ChsDao dao = new ChsDao();

        // selectメソッドのテスト
        System.out.println("---------- select()のテスト ----------");
        Chs searchParam = new Chs(0,"英語", "ここは英語を勉強したい人が集まるチャンネルです。", "2019-12-25 21:05:00");
        searchParam.setChName("");  // Modify as needed for specific search criteria

        List<Chs> channelList = dao.select();
        for (Chs channel :channelList ) {
            System.out.println("チャンネルID ：" + channel.getChannelId());
            System.out.println("チャンネルネーム：" + channel.getChName());
            System.out.println("チャンネルコメント：" + channel.getChComment());
            System.out.println("チャンネル作成日付：" + channel.getCreatedAt());
            System.out.println();
        }
    }
}

