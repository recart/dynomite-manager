package com.netflix.nfsidecar.instance;

import com.netflix.nfsidecar.utils.SystemUtils;

/**
 * Calls AWS ec2 metadata to get info on the location of the running instance.
 *
 */
public class AwsInstanceDataRetriever implements InstanceDataRetriever {
    public String getRac() {
        return SystemUtils.getDataFromUrl("http://169.254.169.254/latest/meta-data/placement/availability-zone");
    }

    public String getPublicHostname() {
        // Using local-hostname because of private-vpc
        return SystemUtils.getDataFromUrl("http://169.254.169.254/latest/meta-data/local-hostname");
    }

    public String getPublicIP() {
        // Using local-ipv4 because of private-vpc
        return SystemUtils.getDataFromUrl("http://169.254.169.254/latest/meta-data/local-ipv4");
    }

    public String getInstanceId() {
        return SystemUtils.getDataFromUrl("http://169.254.169.254/latest/meta-data/instance-id");
    }

    public String getInstanceType() {
        return SystemUtils.getDataFromUrl("http://169.254.169.254/latest/meta-data/instance-type");
    }

    @Override
    /*
     * @return id of the network interface for running instance
     */
    public String getMac() {
        return SystemUtils.getDataFromUrl("http://169.254.169.254/latest/meta-data/network/interfaces/macs/").trim();
    }

    @Override
    public String getVpcId() {
        throw new UnsupportedOperationException("Not applicable as running instance is in classic environment");
    }

}
