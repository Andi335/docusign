package com.forcont.digsigproto.common.api;

import com.forcont.digsigproto.common.model.DigSigModel;

/**
 * Interface for implementing converters between related model classes from diffrent APIs.
 * @param <M> DigSig model class.
 * @param <T> Signing provider model class.
 */
public interface ModelConverter<M extends DigSigModel, T>
{
    /**
     * Converts a signing provider model class to {@link DigSigModel}.
     * @param t Signing provider model.
     * @return Rrelated {@link DigSigModel}.
     */
    M convertFromProvider(T t);

    /**
     * Converts a {@link DigSigModel} to a signing provider model class.
     * @param digsigModel {@link DigSigModel} related to the signing provider model.
     * @return related signing provider model class.
     */
    T convertToProvider(M digsigModel);
}

