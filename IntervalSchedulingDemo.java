import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

// implementation of greedy interval scheduling algorithm
// selects jobs with earliest finish time that are compatible with the previously selected jobs
public class IntervalSchedulingDemo {

    public static void main(String[] args) {
        
        LinkedList<Job> jobList;
        LinkedList<Job> selectedJobs;
        
        // build job list
        jobList = new LinkedList<>();
        jobList.add(new Job("A", 0, 6));
        jobList.add(new Job("B", 1, 4));
        jobList.add(new Job("C", 3, 5));
        jobList.add(new Job("D", 3, 8));
        jobList.add(new Job("E", 4, 7));
        jobList.add(new Job("F", 5, 9));
        jobList.add(new Job("G", 6, 10));
        jobList.add(new Job("H", 8, 11));

        ////////////////////////////////////////////////////////////////////
        //
        // implement greedy interval scheduling to select max subset of jobs
        //
        ////////////////////////////////////////////////////////////////////

        Collections.sort(jobList, new Job.compareByFinish());
        
        selectedJobs = new LinkedList<>();
        for (Job j : jobList)
            if (isCompatible(j, selectedJobs))
               selectedJobs.add(j); // add first compatible job
        
        System.out.println("Given this job list, this resource can complete " 
                + selectedJobs.size() + " jobs:");
        System.out.print("Jobs: ");
        printJobList(selectedJobs);
        System.out.print("Intervals: ");
        printJobIntervals(selectedJobs);

        ////////////////////////////////////////////////////////////////////
        // end of interval scheduling algorithm
        ////////////////////////////////////////////////////////////////////
    }
    
    private static boolean isCompatible(Job j, LinkedList<Job> jobs) {
        return jobs.peekLast() == null ? true : j.getStart() >= jobs.peekLast().getFinish();
    }
    
    private static void printJobList(LinkedList<Job> list) {
        System.out.print("{ ");
        for (Job j : list)
            System.out.print(j.getLabel() + " ");
        System.out.println("}");
    }
    
    private static void printJobIntervals(LinkedList<Job> list) {
        System.out.print("[ ");
        for (Job j : list)
            System.out.print(j.getLabel() + ":(" + j.getStart() + ", " + j.getFinish() + ") ");
        System.out.println("]");
    }
}

class Job {
        private String label;
        private int start;
        private int finish;
        
        public Job(String l, int s, int f) {
            label = l;
            start = s;
            finish = f;
        }
        
        public String getLabel() {
            return label;
        }

        public int getStart() {
            return start;
        }
        
        public int getFinish() {
            return finish;
        }
    
    static class compareByStart implements Comparator<Job> {

        @Override
        public int compare(Job j1, Job j2) {
            return j1.start - j2.start;
        }
    }
    
    static class compareByFinish implements Comparator<Job> {

        @Override
        public int compare(Job j1, Job j2) {
            return j1.finish - j2.finish;
        }
    }
}