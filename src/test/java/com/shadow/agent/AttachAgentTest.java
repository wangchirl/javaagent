package com.shadow.agent;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.sun.tools.attach.spi.AttachProvider;

import java.util.List;
import java.util.Properties;

public class AttachAgentTest {
    public static void main(String[] args) throws Exception {

        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            String id = vmd.id();
            String displayName = vmd.displayName();
            AttachProvider provider = vmd.provider();
            System.out.println("Id: " + id);
            System.out.println("Name: " + displayName);
            System.out.println("Provider Name: " + provider.name());
            System.out.println("Provider Type: " + provider.type());
            System.out.println("Provider Impl: " + provider.getClass());
            System.out.println("=====================");
            if (displayName.equals("com.shadow.Application")) {
                VirtualMachine vm = VirtualMachine.attach(vmd);
                Properties systemProperties = vm.getSystemProperties();
                Properties agentProperties = vm.getAgentProperties();
                System.out.println("systemProperties : " + systemProperties);
                System.out.println("agentProperties : " + agentProperties);
                // attach
                String agentPath = "F:\\source\\javaagent\\target\\SuperAgent-jar-with-dependencies.jar";
                vm.loadAgent(agentPath, "proxyType=asma&debug=true&originJobType=xxl&jobType=simple&ctlClass=com.shadow.controller.AgentBaseController&tlClass=com.shadow.supports.framework.ScheduleService&tlFieldName=JOB_PARAMETERS_THREAD_LOCAL");
                vm.detach();
                break;
            }
        }
    }
}
