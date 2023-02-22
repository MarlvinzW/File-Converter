package zw.dreamhub.services;

import org.springframework.http.ResponseEntity;
import zw.dreamhub.domain.dto.request.FileUploaderRequest;

import java.util.List;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/2/2023
 */
public interface FileService {
    ResponseEntity<Object> convertCsvToExcel(FileUploaderRequest request);

    ResponseEntity<List<List<String>>> readCsvFile(FileUploaderRequest request);
}
