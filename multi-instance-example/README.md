<p align="center">
    <img src="../.github/images/camunda.png" alt="camunda" title="camunda"/>
    <h1 align="center">Camunda Heatmap examples</h1>
</p>

This is a simple [camunda](http://www.camunda.org) process application, that demonstrates a process containing a multi instance user task. If you have the requirement to have boundary events that quit all instances, you need a surrounding transaction. Therefore, this example would not work:

# Screenshots

![Screenshot](src/main/resources/multi-instance-task.png?raw=true "Screenshot")