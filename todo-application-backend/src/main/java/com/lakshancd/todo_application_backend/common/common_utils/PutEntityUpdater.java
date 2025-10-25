package com.lakshancd.todo_application_backend.common.common_utils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class for updating an existing entity with values from an update request.
 *
 * @param <T> Type of the existing entity.
 * @param <U> Type of the update request.
 */
public class PutEntityUpdater<T, U> {
    private final Set<String> skipFields;

    // No-args constructor (default, skips nothing)
    public PutEntityUpdater() {
        this.skipFields = new HashSet<>();
    }

    // Parameterized constructor (skips specified fields)
    public PutEntityUpdater(Set<String> skipFields) {
        this.skipFields = skipFields != null ? skipFields : new HashSet<>();
    }
    /**
     * Updates the fields of the existing entity with non-null values from the update request.
     *
     * @param existingEntity The existing entity to be updated.
     * @param updateRequest  The update request containing new values.
     * @return True if any field in the existing entity is updated, false otherwise.
     */
    public boolean updateEntity(T existingEntity, U updateRequest) {
        boolean isChanged = false;
        try {
            // Find the modifiedDate field in the existing entity
            Field modifiedDateField = existingEntity.getClass().getSuperclass().getDeclaredField("modifiedDate");
            modifiedDateField.setAccessible(true); // Ensure accessibility

           // Set the modifiedDate field to the current date and time
            modifiedDateField.set(existingEntity, new Date());
        }catch (Exception e){
            System.err.println("Error: NoSuchFieldException - " + e.getMessage());
        }

        // Iterate through all fields in the existing entity
        for (Field field : existingEntity.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                // Get the current value of the field in the existing entity
                Object existingValue = field.get(existingEntity);

                // Skip the update for specific fields (e.g., isActive and isArchived)
                if (skipFields.contains(field.getName()) || "isActive".equals(field.getName()) || "isArchived".equals(field.getName())) {
                    continue;
                }

                // Find the corresponding field in the update request
                Field updateField = updateRequest.getClass().getDeclaredField(field.getName());
                updateField.setAccessible(true);

                // Get the value from the update request
                Object updateValue = updateField.get(updateRequest);

                // Update the field in the existing entity if the value is not-null and different
                if (updateValue != null && !updateValue.equals(existingValue)) {
                    field.set(existingEntity, updateValue);
                    isChanged = true;
                }
            } catch (NoSuchFieldException e) {
                System.err.println("Error: NoSuchFieldException - " + e.getMessage());
            } catch (IllegalAccessException e) {
                System.err.println("Error: IllegalAccessException - " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isChanged;
    }
}