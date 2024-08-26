package controller;

import it.unicam.cs.mpmgc.vectorrally.api.controller.builders.TrackPathBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackPathBuilderTest {

    private TrackPathBuilder trackPathBuilder;

    @BeforeEach
    void setUp() {
        trackPathBuilder = new TrackPathBuilder();
    }

    @Test
    void shouldReturnCorrectRootPathWhenEndingWithApp() {
        System.setProperty("user.dir", "/some/path/app");
        String rootPath = TrackPathBuilder.checkRootPath();
        assertEquals("../api/src/main/resources/racetracks", rootPath);
    }

    @Test
    void shouldReturnTrueWhenDirectoryExists() {
        File mockDirectory = mock(File.class);
        when(mockDirectory.exists()).thenReturn(true);
        when(mockDirectory.isDirectory()).thenReturn(true);
        boolean result = trackPathBuilder.doesDirectoryExist(mockDirectory);
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDirectoryDoesNotExist() {
        File mockDirectory = mock(File.class);
        when(mockDirectory.exists()).thenReturn(false);
        boolean result = trackPathBuilder.doesDirectoryExist(mockDirectory);
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenFilesExist() {
        File[] files = new File[]{new File("track1.txt")};
        boolean result = trackPathBuilder.doFilesExist(files);
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenNoFilesExist() {
        File[] files = new File[]{};
        boolean result = trackPathBuilder.doFilesExist(files);
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenFilesArrayIsNull() {
        boolean result = trackPathBuilder.doFilesExist(null);
        assertFalse(result);
    }
}

