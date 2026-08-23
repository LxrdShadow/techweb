package com.project.techweb.occuper;

import java.time.LocalDate;

public record OccuperRequest(Integer codeprof, Integer codesal, LocalDate date) {
}
