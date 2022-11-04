package com.purpleprint.network.purpleprintproject.common.dto;

/**
 * <pre>
 * Class : ChildDTO
 * Comment: User 정보에 반환될 child 정보
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
public class ChildDTO {

    private int childId;
    private String childName;
    private int connectNum;
    private int grantHeart;
    private int givenHeart;

    public ChildDTO() {}

    public ChildDTO(int childId, String childName, int connectNum, int grantHeart, int givenHeart) {
        this.childId = childId;
        this.childName = childName;
        this.connectNum = connectNum;
        this.grantHeart = grantHeart;
        this.givenHeart = givenHeart;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public int getConnectNum() {
        return connectNum;
    }

    public void setConnectNum(int connectNum) {
        this.connectNum = connectNum;
    }

    public int getGrantHeart() {
        return grantHeart;
    }

    public void setGrantHeart(int grantHeart) {
        this.grantHeart = grantHeart;
    }

    public int getGivenHeart() {
        return givenHeart;
    }

    public void setGivenHeart(int givenHeart) {
        this.givenHeart = givenHeart;
    }

    @Override
    public String toString() {
        return "ChildDTO{" +
                "childId=" + childId +
                ", childName='" + childName + '\'' +
                ", connectNum=" + connectNum +
                ", grantHeart=" + grantHeart +
                ", givenHeart=" + givenHeart +
                '}';
    }
}
