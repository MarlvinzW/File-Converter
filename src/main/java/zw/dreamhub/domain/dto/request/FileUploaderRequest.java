package zw.dreamhub.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/2/2023
 */
public record FileUploaderRequest(@NotNull MultipartFile file) { }
