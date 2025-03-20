package diastek.utils;

import java.io.File;

import static diastek.constants.FrameworkConstants.REPORTS_CUCUMBER_LOCATION;
import static diastek.constants.FrameworkConstants.REPORTS_ZIPPED_FILE_NAME;

import org.zeroturnaround.zip.ZipUtil;

public class ZipUtils {
    public static void zip() {
        ZipUtil.pack(new File(REPORTS_CUCUMBER_LOCATION), new File(REPORTS_ZIPPED_FILE_NAME));
    }

}
