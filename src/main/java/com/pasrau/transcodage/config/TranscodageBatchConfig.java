package com.pasrau.transcodage.config;

import com.pasrau.transcodage.listener.PasrauStepListener;
import com.pasrau.transcodage.mapper.CustomFieldSetMapper;
import com.pasrau.transcodage.model.FlatPasrauItemReader;
import com.pasrau.transcodage.model.IndividuData;
import com.pasrau.transcodage.step.PasrauTranscodageProcessor;
import com.pasrau.transcodage.step.PasrauTranscodageWriter;
import com.pasrau.transcodage.step.PasrauTransodageReader;
import com.pasrau.transcodage.model.TranscodagePasrauDataStore;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class TranscodageBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name=("pasrauTranscodageJob"))
    public Job pasrauTranscodageStep(Step pasrauStep){
        return jobBuilderFactory.get("pasrauTranscodageJob")
                .incrementer(new RunIdIncrementer())
                .start(pasrauTranscodageStep())
                .build();
    }

    @Bean(name = "pasrauTranscodageStep")
    public Step pasrauTranscodageStep(){
        return stepBuilderFactory.get("pasrauTranscodageStep")
                .<FlatPasrauItemReader, List<IndividuData>>chunk(1)
                .reader(transodageReader(null))
                .processor(pasrauItemProcessor())
                .writer(pasrauTranscodageWriter())
                .listener(stepPasreauListener())
                .build();
    }

    @Bean
    public PasrauTransodageReader transodageReader(@Qualifier("pasrauLineMapper") LineMapper<IndividuData> pasrauLineMapper) {
         return new PasrauTransodageReader(pasrauLineMapper);
    }

    @Bean(name="pasrauLineMapper")
    public LineMapper<IndividuData> pasrauLineMapper() {
        DefaultLineMapper<IndividuData> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer(",");
        lineTokenizer.setNames("blockInfo", "value");
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(customFieldSetMapper());
        return lineMapper;
    }

    @Bean(name="customerFieldMapper")
    public FieldSetMapper<IndividuData> customFieldSetMapper() {
        return new CustomFieldSetMapper();
    }

    @Bean
    public PasrauTranscodageProcessor pasrauItemProcessor() {
        return new PasrauTranscodageProcessor();
    }
    @Bean
    public PasrauTranscodageWriter pasrauTranscodageWriter() {
        return  new PasrauTranscodageWriter();
    }
    @Bean
    public PasrauStepListener stepPasreauListener() {
        return new PasrauStepListener();
    }

    @Bean(name="dataStore")
    public TranscodagePasrauDataStore transcodagePasrauDataStore() {
        return new TranscodagePasrauDataStore();
    }

    @Bean(name ="executionContext")
    public  ExecutionContext executionContext(){
        return new ExecutionContext();
    }
}
