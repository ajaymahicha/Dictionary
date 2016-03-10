package com.example.asderfers.dictionary;

public class words {
    int _id;
    String _word;
    String _meaning;
    int _ratio;
    byte[] _imgdata;
    public words(int id ,String word,String mean,int ratio ,byte[] img)
    {
        this._id=id;
        this._word=word;
        this._meaning=mean;
        this._ratio=ratio;
        this._imgdata=img;
    }
    public int getID()
    {
        return this._id;
    }
    public String getWord()
    {
        return this._word;
    }
    public String getMean()
    {
        return this._meaning;
    }
    public int getRatio()
    {
        return this._ratio;
    }

    public byte[] getImageData()
    {
        return this._imgdata;
    }
}
