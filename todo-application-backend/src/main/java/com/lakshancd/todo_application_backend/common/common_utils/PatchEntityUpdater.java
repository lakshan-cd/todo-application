package com.lakshancd.todo_application_backend.common.common_utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * Utility class for updating entity properties based on a map of updates.
 *
 * @param <T> The type of the entity to be updated.
 */
public class PatchEntityUpdater<T> {
    private static final Logger logger = LoggerFactory.getLogger(PatchEntityUpdater.class);
    /**
     * Updates the properties of the given entity using a map of updates.
     *
     * @param entity       The entity to be updated.
     * @param updates      A map containing property names and their updated values.
     * @param propertyUpdater A callback interface for updating properties.
     * @return True if any property is updated, false otherwise.
     */
    public boolean updateProperties(T entity, Map<String, Object> updates, PropertyUpdater<T> propertyUpdater) {

        boolean isChanged = false;
        try {
            // Find the modifiedDate field in the existing entity
            Field modifiedDateField = entity.getClass().getSuperclass().getDeclaredField("modifiedDate");
            modifiedDateField.setAccessible(true); // Ensure accessibility

            // Set the modifiedDate field to the current date and time
            modifiedDateField.set(entity, new Date());
        }catch (Exception e){
            System.err.println("Error: NoSuchFieldException - " + e.getMessage());
        }

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String propertyName = entry.getKey();
            Object updatedValue = entry.getValue();
            try {
                // Log the update operation
                logger.info("Updating property '{}' with value '{}'", propertyName, updatedValue);
                propertyUpdater.updateProperty(entity, propertyName, updatedValue);
                isChanged = true;

            }catch (Exception e) {
                // Log the exception or throw a custom exception
                logger.error("Error updating entity property: " + e.getMessage(), e);
            }
        }
        return isChanged;
    }

    /**
     * Callback interface for updating properties of a specific entity type.
     *
     * @param <T> The type of the entity to be updated.
     */
    public interface PropertyUpdater<T> {

        /**
         * Updates a property of the entity.
         *
         * @param entity       The entity to be updated.
         * @param propertyName The name of the property.
         * @param updatedValue The updated value for the property.
         */
        void updateProperty(T entity, String propertyName, Object updatedValue) throws ParseException;

    }
}