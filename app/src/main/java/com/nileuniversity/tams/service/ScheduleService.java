package com.nileuniversity.tams.service;

import com.nileuniversity.tams.model.Schedule;
import java.util.ArrayList;
import java.util.List;

/**
 * ScheduleService
 * Manages class schedules
 * Uses in-memory storage
 */
public class ScheduleService {
    private static ScheduleService instance;
    private List<Schedule> schedules;

    /**
     * Private constructor for Singleton pattern
     */
    private ScheduleService() {
        schedules = new ArrayList<>();
        initializeSampleSchedules();
    }

    /**
     * Get singleton instance
     */
    public static ScheduleService getInstance() {
        if (instance == null) {
            instance = new ScheduleService();
        }
        return instance;
    }

    /**
     * Initialize sample schedules
     */
    private void initializeSampleSchedules() {
        schedules.add(new Schedule("CS101", "Software Engineering", "08:00", "10:00", "Room A101"));
        schedules.add(new Schedule("CS102", "Data Structures", "10:00", "12:00", "Room A102"));
        schedules.add(new Schedule("CS103", "Algorithms", "13:00", "15:00", "Room A103"));
    }

    /**
     * Add a new schedule
     */
    public boolean addSchedule(Schedule schedule) {
        // Check for conflicts
        for (Schedule existing : schedules) {
            if (schedule.checkConflict(existing)) {
                return false; // Conflict detected
            }
        }
        
        schedule.addEntry();
        schedules.add(schedule);
        return true;
    }

    /**
     * Remove a schedule
     */
    public boolean removeSchedule(String classId) {
        return schedules.removeIf(schedule -> schedule.getClassId().equals(classId));
    }

    /**
     * Update schedule
     */
    public boolean updateSchedule(String classId, String startTime, String endTime, String room) {
        for (Schedule schedule : schedules) {
            if (schedule.getClassId().equals(classId)) {
                schedule.setStartTime(startTime);
                schedule.setEndTime(endTime);
                schedule.setRoom(room);
                return true;
            }
        }
        return false;
    }

    /**
     * Get all schedules
     */
    public List<Schedule> getAllSchedules() {
        return new ArrayList<>(schedules);
    }

    /**
     * Get schedule by class ID
     */
    public Schedule getScheduleById(String classId) {
        for (Schedule schedule : schedules) {
            if (schedule.getClassId().equals(classId)) {
                return schedule;
            }
        }
        return null;
    }

    /**
     * Get schedules by room
     */
    public List<Schedule> getSchedulesByRoom(String room) {
        List<Schedule> roomSchedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            if (schedule.getRoom().equalsIgnoreCase(room)) {
                roomSchedules.add(schedule);
            }
        }
        return roomSchedules;
    }

    /**
     * Get schedules by time
     */
    public List<Schedule> getSchedulesByTime(String startTime) {
        List<Schedule> timeSchedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            if (schedule.getStartTime().equals(startTime)) {
                timeSchedules.add(schedule);
            }
        }
        return timeSchedules;
    }

    /**
     * Check if a time slot is available
     */
    public boolean isTimeSlotAvailable(String startTime, String endTime, String room) {
        Schedule tempSchedule = new Schedule("TEMP", "Temp", startTime, endTime, room);
        for (Schedule schedule : schedules) {
            if (tempSchedule.checkConflict(schedule)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get total schedules count
     */
    public int getTotalSchedulesCount() {
        return schedules.size();
    }
}
