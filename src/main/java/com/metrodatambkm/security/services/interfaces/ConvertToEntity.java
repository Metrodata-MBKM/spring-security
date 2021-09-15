package com.metrodatambkm.security.services.interfaces;

public interface ConvertToEntity <E, R>{
    E convert(R r);
}
