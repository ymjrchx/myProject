package com.dgg.Bean;

public class PhoneInfo {
    private Long id;
    private String src;
    private String dst;
    private Integer callsec;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public Integer getCallsec() {
        return callsec;
    }

    public void setCallsec(Integer callsec) {
        this.callsec = callsec;
    }

    @Override
    public String toString() {
        return "PhoneInfo{" +
                "id=" + id +
                ", src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                ", callsec=" + callsec +
                '}';
    }
}
