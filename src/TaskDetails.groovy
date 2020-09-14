import org.dxworks.inspectorgit.model.issuetracker.DetailedIssue

import static org.dxworks.inspectorgit.services.ScriptUtilsKt.exportCsv
import static org.dxworks.inspectorgit.services.ScriptUtilsKt.getSystem

def allIssues = system.issueProjects.values().collect { it.issueRegistry.allDetailedIssues }.flatten() as List<DetailedIssue>
def issueDetails = allIssues.collect {
    [taskId       : it.id,
     commits      : it.commits.size(),
     authors      : it.commits.collect { it.author }.unique().size(),
     jiraChanges  : it.changes.size(),
     currentStatus: it.status.name,
     comments     : it.comments.size(),
     pullRequests : it.pullRequests.size()]
}

exportCsv(issueDetails, "issueDetails")
