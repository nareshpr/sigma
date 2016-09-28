# DataRPM μFlow
### The Micro Workflow for Java Developers

Key Features:

1. Annotation-based Definition.
2. Ability to define Fixed or Conditional Flow
3. Ability to create Dynamic Flow Definition, based on Run Time context.
4. Ability to share data with different States of a Flow.
5. Execution Thread Pool control.

### Linking
You can link against this library in your program at the following coordinates:

Using Maven:
```
<dependency>
    <groupId>com.datarpm.sigma</groupId>
    <artifactId>datarpm-sigma-core</artifactId>
    <version>1.0</version>
</dependency>
```

### Requirements
You need to have a 1.6+ version of Java installed.

### Simple Annotation Flow

### Conditional Annotation Flow

### Dynamic Workflow Plan

### Control Concurrency

```
WorkflowEngineConfig config = new WorkflowEngineConfig();
config.setWorkerThreadCount(NUMBER_OF_THREADS);
WorkflowEngine engine = new WorkflowEngine(config);
```

### Building from source

simply run script ```./sbuild/build.sh``` in cloned directory

### License
```
This software is licensed under the Apache License, version 2 ("ALv2"), quoted below.

Copyright 2012-2016 DataRPM <http://datarpm.com>

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```
