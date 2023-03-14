package cn.tongdun.mobrisk.core.collectors;

import java.io.File;

import cn.tongdun.mobrisk.core.utils.Constants;
import cn.tongdun.mobrisk.core.utils.EnvUtils;

/**
 * @author collam
 * @date 2023/3/14
 */
public class MagiskCollector {
    public boolean detectMagisk(){
        return detectMagiskByFile() || detectMagiskByLastModified();
    }

    private boolean detectMagiskByLastModified(){
        File mountsFile = new File(Constants.MAGISK_MOUNTS_PATH);
        File mountInfoFile = new File(Constants.MAGISK_MOUNT_INFO_PATH);
        File mountStatsFile = new File(Constants.MAGISK_MOUNT_STATS_PATH);

        if(mountsFile.lastModified() == mountInfoFile.lastModified()
                && mountInfoFile.lastModified() == mountStatsFile.lastModified()){
            return false;
        }
        return true;
    }

    private boolean detectMagiskByFile(){
        return EnvUtils.fileInEnv("magisk");
    }
}
