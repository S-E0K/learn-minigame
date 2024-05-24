package org.se0k.learnminigame;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Learn_minigame extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getCommandMap().register("minigame", new CommandEvent("minigame"));

    }

    @Override
    public void onDisable() {

    }
}
/*
1. 일정 크기의 아레나, 테두리는 월드보더
2. 라운드 - 등장 몬스터 모두 처치 시 클리어, 10초 후 다음 라운드, 몬스터 체력, 마릿수 등 난이도 증가
3. 사망하면 끝 - 클리어한 라운드 정보 출력, 아레나 초기화
4. 난이도별 클리어한 최고 라운드, 평균 라운드 기록 - 서버 재시작해도 유지


아레나
기본 - 30 * 30, 몬스터 배치 좌표 하나
일정 크기의 블럭 생성, 제거 - 난이도에 따라 크기가 달라짐

라운드 보상
무기
검, 도끼 등 사용할 수 있는 아이템 지급
특정 아이템은 우클릭으로 스킬 사용 가능 - 임의 제작
시작, 종료

회복
포션과 음식 등의 아이템 지급

버프
임의로 구성한 버프 효과 지급

몬스터
미식몹 플러그인 사용
생성, 편집, 몬스터 수 카운트

플레이어
각 플레이어별 Json 파일에 저장
무기 지급, 회복 아이템 지급, 버프 적용, 사망 체크
*/