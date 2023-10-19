package com.scci.utlity

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import com.scci.democalculator.R


class SoundPlay {
    private val TAG = SoundPlay::class.java.simpleName

    private var mSucessMediaPlayer: MediaPlayer? = null



    constructor(context: Context?) {
        mSucessMediaPlayer = MediaPlayer.create(context, R.raw.gift_ringtone)
        mSucessMediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)


    }

    fun playSuccess() {
        if (null != mSucessMediaPlayer) {

            mSucessMediaPlayer!!.seekTo(0)
            mSucessMediaPlayer!!.start()
        } else {

        }
    }




    fun close() {
        if (null != mSucessMediaPlayer) {
            if (mSucessMediaPlayer!!.isPlaying) {
                mSucessMediaPlayer!!.stop()
            }
            mSucessMediaPlayer!!.release()
            mSucessMediaPlayer = null
        }

    }
}