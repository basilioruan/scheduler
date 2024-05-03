package com.projects.scheduler.outbound.adapters;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.mocks.StudentLevelMocks;
import com.projects.scheduler.outbound.mappers.StudentLevelEntityMapper;
import com.projects.scheduler.outbound.persistence.repositories.StudentLevelRepository;
import com.projects.scheduler.utils.DefaultValues;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentLevelAdapterTests {

    @InjectMocks
    private StudentLevelAdapter studentLevelAdapter;

    @Mock
    private StudentLevelRepository studentLevelRepository;

    @Mock
    private StudentLevelEntityMapper studentLevelEntityMapper;

    @Test
    void findById_shouldReturnNull() {
        StudentLevel actual = this.studentLevelAdapter.findById(null);

        assertThat(actual).isNull();
    }

    @Test
    void findById_shouldReturnDomain() {
        StudentLevel expected = StudentLevelMocks.getStudentLevelDomain();

        BDDMockito.when(this.studentLevelRepository.getReferenceById(anyLong())).thenReturn(StudentLevelMocks.getStudentLevelEntity());
        BDDMockito.when(this.studentLevelEntityMapper.fromEntity(any())).thenCallRealMethod();

        StudentLevel actual = this.studentLevelAdapter.findById(DefaultValues.LONG_VALUE);

        assertThat(actual).isNotNull();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void findAll_shouldReturnEmptyList() {
        BDDMockito.when(this.studentLevelRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<StudentLevel> actual = this.studentLevelAdapter.findAll();

        assertThat(actual).isEmpty();
    }

    @Test
    void findAll_shouldReturnDomainList() {
        List<StudentLevel> expected = List.of(StudentLevelMocks.getStudentLevelDomain());

        BDDMockito.when(this.studentLevelRepository.findAll()).thenReturn(List.of(StudentLevelMocks.getStudentLevelEntity()));
        BDDMockito.when(this.studentLevelEntityMapper.fromEntity(any())).thenCallRealMethod();

        List<StudentLevel> actual = this.studentLevelAdapter.findAll();

        assertThat(actual).isNotEmpty();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void save_shouldReturnNull() {
        StudentLevel actual = this.studentLevelAdapter.save(null);

        assertThat(actual).isNull();
    }

    @Test
    void save_shouldReturnSavedDomain() {
        StudentLevel expected = StudentLevelMocks.getStudentLevelDomain();

        BDDMockito.when(this.studentLevelRepository.save(any())).thenReturn(StudentLevelMocks.getStudentLevelEntity());
        BDDMockito.when(this.studentLevelEntityMapper.fromEntity(any())).thenCallRealMethod();

        StudentLevel actual = this.studentLevelAdapter.save(StudentLevelMocks.getStudentLevelDomain());

        assertThat(actual).isNotNull();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void deleteById_shouldNotCallRepositoryDelete() {
        this.studentLevelAdapter.deleteById(null);

        verify(this.studentLevelRepository, times(0)).deleteById(anyLong());
    }

    @Test
    void deleteById_shouldCallRepositoryDelete() {
        this.studentLevelAdapter.deleteById(DefaultValues.LONG_VALUE);

        verify(this.studentLevelRepository, times(1)).deleteById(anyLong());
    }

}
