import java.util.*;

// Time Complexity : O(V+E) = ~O(max(V,E)), where V are the vertices in the graph, E are the edges between the vertices
// Space Complexity : O(2V) = ~O(V), indegree array + adjacency list
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach :
// We can visualize the courses as the nodes in a graph where the relation is one course is a pre-req for the other course.
// So it becomes directed graph where course A needs to be visited first, inorder to reach to course B. So we will start
// with the courses that have no dependency, add them to the queue, once this course is done, we can reduce it dependency
// on other courses by 1 and add new courses to the queue which have no pre-reqs.
// We should keep the count of pre-requisites for a course in an array, also called as dependencies or indegrees array.
// Also, we can keep adjacency list to do quick search on which course is pre-req for other nodes. So, once all the courses
// in the queue have been finished and queue is empty, we check if there are still untouched edges indegrees array, if yes
// it is not possible to take all the courses and return false;
class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int edgeCount = 0;

        // Create the adjacency list with prereq --> {c1, c2...} format
        HashMap<Integer, List<Integer>> coursesMap = new HashMap<>();
        for (int[] courses : prerequisites){
            int course = courses[0];
            int prereq = courses[1];
            if(!coursesMap.containsKey(prereq)){
                coursesMap.put(prereq, new ArrayList<>());
            }
            coursesMap.get(prereq).add(course);
            // maintain the indegrees count for all the courses
            indegree[course] += 1;
            edgeCount++;
        }

        // if there are no edges, there is no prerequisites, thus all courses can be taken
        if(edgeCount == 0) {
            return true;
        }

        Queue<Integer> coursesOrder = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0){
                // Put the courses that have no pre-reqs in the queue
                coursesOrder.add(i);
            }
        }

        while(!coursesOrder.isEmpty()){
            Integer currentCourse = coursesOrder.poll();
            List<Integer> childrenCourses = coursesMap.get(currentCourse);
            if(childrenCourses == null) continue;
            // Once the pre-req is done, reduce the indegree for other course
            for(int course : childrenCourses){
                indegree[course] -= 1;
                edgeCount-= 1;
                // If course is not dependent on any other course, add it to the queue
                if(indegree[course] == 0){
                    coursesOrder.add(course);
                }
            }
        }

        return edgeCount == 0;
    }
}
