package sudoku

import com.google.common.collect.Lists
import com.google.common.collect.Maps


class Report {
    Map<String, Collection<Cell>> reportCells = Maps.newHashMap();
    List<String> reports = Lists.newArrayList();
    
    void add(String report) {
        reports.add(report)
        reportCells.put(report, Lists.newArrayList())
    }

    void add(String report, Collection<Cell> cells) {
        reports.add(report);
        reportCells.put(report, cells);
    }
    
    void add(Report report) {
        reports.addAll(report.reports)
        reportCells.putAll(report.reportCells)
    }
}
