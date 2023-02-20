package zw.dreamhub.services;

import org.springframework.http.ResponseEntity;
import zw.dreamhub.domain.dto.request.FileUploaderRequest;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/2/2023
 */
public interface FileService {
    ResponseEntity<?> convertCsvToExcel(FileUploaderRequest request);
}
