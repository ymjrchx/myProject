package com.dgg.Bean;

/**
 * @Classname DggBusinessSocreDetail
 * @Description 二级业态商务积分明细
 * @Date 2019/7/26 16:38
 * @Created by dgg-yanshun
 */
public class DggBusinessSocreDetail {

    private Double num;//值
    private String name;//名字
    private Integer rank;//排名
    private Double score;//分数
    private Double weight;//权重
    private Integer range;//范围

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "DggBusinessSocreDetail{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", score=" + score +
                ", weight=" + weight +
                ", range=" + range +
                '}';
    }
}
