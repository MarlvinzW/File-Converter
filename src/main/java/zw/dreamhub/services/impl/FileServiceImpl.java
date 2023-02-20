package zw.dreamhub.services.impl;

import com.aspose.cells.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.dreamhub.domain.dto.request.FileUploaderRequest;
import zw.dreamhub.services.FileService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/2/2023
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private static final String XLS_FORMAT = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String CSV_FORMAT = "text/csv";
    private static final String HOME_DIR = System.getProperty("user.dir");
    private static final String MEDIA_DIR = "media";

    @Override
    public ResponseEntity<?> convertCsvToExcel(FileUploaderRequest request) {

        // validate for
        if (Objects.equals(request.file().getContentType(), XLS_FORMAT)) {

            // media path
            String mediaPath = HOME_DIR + File.separator + MEDIA_DIR;

            // destination creation
            File storage = new File(mediaPath);
            if (!storage.exists()) {
                storage.mkdirs();
            }

            // xlsx file name
            String filePath = mediaPath + File.separator + request.file().getOriginalFilename();
            File file = new File(filePath);

            try {
                // saving file to media dir
                request.file().transferTo(file);

                // csv to xlsx conversion
                Workbook workbook = new Workbook(filePath);
                String csvPath = mediaPath + File.separator +
                        request.file().getOriginalFilename() + ".csv";

                // formatting file path
                String formattedCsvPath = csvPath
                        .replace(".xlsx", "")
                        .trim();

                // convert
                workbook.save(formattedCsvPath);

                // csv file
                File csvFile = new File(formattedCsvPath);

                // response
                return ResponseEntity.ok()
                        .contentLength(csvFile.length())
                        .contentType(MediaType.parseMediaType(CSV_FORMAT))
                        .body(new ByteArrayResource(Files.readAllBytes(Paths.get(csvFile.getAbsolutePath()))));

            }
            // file exception
            catch (Exception e) {
                log.info("File Exception: {}", e.getMessage());
                return ResponseEntity
                        .badRequest()
                        .build();
            }
        }
        // invalid file formats
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .build();
        }

    }
}
