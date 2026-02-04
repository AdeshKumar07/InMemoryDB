package org.example.entity;


public class Entry <T>{
    public T data;
    public long expireTime;

    public Entry(T data, long ttl){
        this.data = data;
        this.expireTime = System.currentTimeMillis() + ttl;
    }

    public Entry(T data){
        this.data = data;
        this.expireTime = -1;

    }

    public boolean isExpired(){
        if(expireTime == -1){
            return false;
        }
        return System.currentTimeMillis() > expireTime;
    }

}
